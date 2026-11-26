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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profilcompose.ui.theme.ProfilComposeTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeveloperScreen(
                        experiences = professionalExperiences(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// DATA CLASS
data class Experience (
    val id: Int,
    val title: String,
    val description: String
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

// SKILLS
@Composable
fun Skill(skill: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(bottom = 6.dp),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 5.dp
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun SkillsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Compétences & Expériences",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Skill(skill = "Java")
            Spacer(modifier = Modifier.weight(1f))
            Skill(skill = "Angular")
            Spacer(modifier = Modifier.weight(1f))
            Skill(skill = "Spring Boot")
            Spacer(modifier = Modifier.weight(1f))
            Skill(skill = "Android")
        }
    }
}

// PROFESSIONAL EXPERIENCES
fun professionalExperiences(): List<Experience> {
    return listOf(
        Experience(
            id = 1,
            title = "Employé de station-service",
            description = "Entretien de la station, nettoyage des pompes, mise en rayon," +
                            " gestion de colis"
        ),
        Experience(
            id = 2,
            title = "Expérience complémentaire",
            description = "La description d'une expérience qui sert d'exemple"
        )
    )
}

@Composable
fun ExperienceCard(experience: Experience, modifier: Modifier = Modifier) {
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
                text = experience.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = experience.description,
                style = MaterialTheme.typography.bodyMedium
            )
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
            ProfileImage()
        }
    }
}

// IMAGE
@Composable
fun ProfileImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = android.R.drawable.ic_menu_send),
        contentDescription = "Envoyer",
        modifier = modifier
    )
}

// SCREENS
@Composable
fun DeveloperScreen(experiences: List<Experience>, modifier: Modifier = Modifier) {
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
            SkillsSection()
        }

        items(items = experiences, key = {exp -> exp.id}) { experience ->
            ExperienceCard(experience = experience)
        }

        item {
            Spacer(Modifier.height(20.dp))
            ContactInformation(info = "m.didier@myskolae.fr")
        }

        item {
            ContactButton()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DeveloperProfilePreview() {
    ProfilComposeTheme {
        DeveloperScreen(
            experiences = professionalExperiences()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkDeveloperProfilePreview() {
    ProfilComposeTheme(darkTheme = true) {
        DeveloperScreen(
            experiences = professionalExperiences()
        )
    }
}
