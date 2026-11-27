package com.example.profilcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profilcompose.ui.theme.ProfilComposeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileContainer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// DATA CLASS
data class Project (
    val id: Int,
    val title: String,
    val description: String,
    val technologies: List<Technology>
)

data class Technology (
    val id: Int,
    val name: String
)

// PROFILE
@Composable
fun ProfileHeader(name: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
@Composable
fun ProfileBox(name: String, role: String, description: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Surface(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.primaryContainer,
            shadowElevation = 8.dp
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                ProfileHeader(
                    name = name,
                    role = role,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Surface(
            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Profil",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// TECHNOLOGIES
@Composable
fun TechnologyChip(tech: Technology, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(bottom = 6.dp),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.onSecondary,
        shadowElevation = 5.dp
    ) {
        Text(
            text = tech.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun TechnologiesSection(technologies: List<Technology>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Technologies utilisées",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(5.dp)) {
            items(items = technologies, key = {t -> t.id}) { t ->
                TechnologyChip(tech = t)
            }
        }
    }
}

fun techFirstProject(): List<Technology> {
    return listOf(
        Technology(id = 1, name = "Python"),
        Technology(id = 2, name = "Tkinter"),
        Technology(id = 3, name = "NumPy")
    )
}

fun techSecondProject(): List<Technology> {
    return listOf(
        Technology(id = 1, name = "Java"),
        Technology(id = 2, name = "Angular"),
        Technology(id = 3, name = "Spring Boot")
    )
}

// PROJECTS
fun getProjects(): List<Project> {
    return listOf(
        Project(
            id = 1,
            title = "Projet développement Python",
            description = "Projet qui consiste à développer une interface graphique avec Tkinter" +
                            " permettant de suivre la trajectoire d'un objet dans une vidéo.",
            technologies = techFirstProject()
        ),
        Project(
            id = 2,
            title = "Projet Java Fullstack",
            description = "Projet de développement Java/Angular qui consiste à développer un" +
                            " chatbot à l'aide d'agents IA.",
            technologies = techSecondProject()
        )
    )
}

@Composable
fun ProjectCard(
    project: Project,
    technologies: List<Technology>,
    isFavorite: Boolean,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium
            )
            TechnologiesSection(technologies)
            Button(onClick = {onFavoriteClick(project.id)}) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarOutline,
                    contentDescription = "Favori",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

// CONTACT
@Composable
fun ContactInformation(info: String, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = info,
        onValueChange = {},
        readOnly = true,
        label = { Text(text = "Contact") },
        modifier = modifier.fillMaxWidth()
    )
}

// CONTACT BUTTON
@Composable
fun ContactButton(modifier: Modifier = Modifier) {
    Button(onClick = {}, modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Contacter", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.width(10.dp))
            SendImage()
        }
    }
}

// IMAGE
@Composable
fun SendImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = android.R.drawable.ic_menu_send),
        contentDescription = "Envoyer",
        modifier = modifier
    )
}

// CONTACT SECTION
@Composable
fun ContactSection(info: String, modifier: Modifier = Modifier) {
    var showContactInfo by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(onClick = { showContactInfo = !showContactInfo }, colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        )) {
            Text(
                text = if (showContactInfo) "Masquer les informations de contact"
                else "Afficher les informations de contact"
            )
        }

        if (showContactInfo) {
            ContactInformation(info)
            ContactButton()
        }
    }
}

// SCREENS
@Composable
fun DeveloperScreen(
    projects: List<Project>,
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    favProjectsIds: List<Int>,
    onFavoriteClick: (Int) -> Unit,
    showFavorites: Boolean,
    onShowFavoritesClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "PROFIL DÉVELOPPEUR",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            ProfileBox(
                name = "Melvin Didier",
                role = "Développeur Java/Angular",
                description = "Élève de l'école ESGI, je poursuis mes études vers un Master en" +
                        " Architecture des logiciels."
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text(text = "Rechercher un projet") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = "${projects.size} projet(s) affiché(s)",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            Button(onClick = onShowFavoritesClick, colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.secondary,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            )) {
                Text(
                    text = if (showFavorites) "Afficher tous les projets"
                           else "Afficher les projets favoris"
                )
            }
        }

        items(items = projects, key = { p -> p.id}) { p ->
            ProjectCard(
                project = p,
                technologies = p.technologies,
                isFavorite = favProjectsIds.contains(p.id),
                onFavoriteClick = { onFavoriteClick(p.id) }
            )
        }

        item {
            ContactSection(info = "m.didier@myskolae.fr")
        }

    }
}

// View Model
class ProfileViewModel: ViewModel() {
    private val allProjects: List<Project> = getProjects()

    private val _uiState = MutableStateFlow(ProjectUiState(projects = allProjects))

    val uiState: StateFlow<ProjectUiState> = _uiState.asStateFlow()

    private fun filterProjects(
        searchQuery: String,
        showOnlyFavorites: Boolean,
        favProjectsIds: List<Int>
    ): List<Project> {
        return allProjects.filter { project ->
            val matches = project.title.contains(searchQuery, ignoreCase = true) ||
                    project.technologies.any { it.name.contains(searchQuery, ignoreCase = true) }

            val matchesFavorites = !showOnlyFavorites || favProjectsIds.contains(project.id)
            matches && matchesFavorites
        }
    }

    fun onSearchQueryChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = newValue,
                projects = filterProjects(
                    searchQuery = newValue,
                    showOnlyFavorites = currentState.showOnlyFavorites,
                    favProjectsIds = currentState.favProjectsIds
                )
            )
        }
    }

    fun onFavoriteClick(projectId: Int) {
        _uiState.update { currentState ->
            val newFavIds =
                if (currentState.favProjectsIds.contains(projectId))
                    currentState.favProjectsIds - projectId
                else currentState.favProjectsIds + projectId

            currentState.copy(
                favProjectsIds = newFavIds,
                projects = filterProjects(
                    searchQuery = currentState.searchQuery,
                    showOnlyFavorites = currentState.showOnlyFavorites,
                    favProjectsIds = newFavIds
                )
            )
        }
    }

    fun onShowOnlyFavoritesClick() {
        _uiState.update { currentState ->
            val newValue = !currentState.showOnlyFavorites

            currentState.copy(
                showOnlyFavorites = newValue,
                projects = filterProjects(
                    searchQuery = currentState.searchQuery,
                    showOnlyFavorites = newValue,
                    favProjectsIds = currentState.favProjectsIds)
            )
        }
    }
}

// CONTAINER
@Composable
fun ProfileContainer(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    DeveloperScreen(
        projects = uiState.projects,
        modifier = modifier,
        searchQuery = uiState.searchQuery,
        onSearchQueryChange = {viewModel.onSearchQueryChange(it)},
        favProjectsIds = uiState.favProjectsIds,
        onFavoriteClick = {viewModel.onFavoriteClick(it)},
        showFavorites = uiState.showOnlyFavorites,
        onShowFavoritesClick = {viewModel.onShowOnlyFavoritesClick()}
    )
}

// UI STATE
data class ProjectUiState(
    val projects: List<Project> = emptyList(),
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val favProjectsIds: List<Int> = emptyList()
)

// PREVIEWS
@Preview(showBackground = true)
@Composable
fun DeveloperProfilePreview() {
    ProfilComposeTheme {
        DeveloperScreen(
            projects = getProjects(),
            searchQuery = "",
            onSearchQueryChange = {},
            favProjectsIds = listOf(),
            onFavoriteClick = {},
            showFavorites = false,
            onShowFavoritesClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkDeveloperProfilePreview() {
    ProfilComposeTheme(darkTheme = true) {
        DeveloperScreen(
            projects = getProjects(),
            searchQuery = "Java",
            onSearchQueryChange = {},
            favProjectsIds = listOf(1, 2),
            onFavoriteClick = {},
            showFavorites = true,
            onShowFavoritesClick = {}
        )
    }
}
