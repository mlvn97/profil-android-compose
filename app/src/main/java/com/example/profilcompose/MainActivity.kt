package com.example.profilcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilcompose.ui.theme.ProfilComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Melvin Didier",
                        role = "Développeur Java/Angular",
                        description = "Diplômé d'une licence Informatique, je m'oriente vers un Master en Architecture des logiciels en alternance chez Capgemini.",
                        skills = listOf("Java", "Angular", "Python"),
                        contact = "m.didier@myskolae.fr",
                        actionLabel = "Contacter",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    contact: String,
    actionLabel: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "PROFIL",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            ProfileCard(
                name = name,
                role = role,
                description = description,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
            skills.forEach { skill ->
                Skill(skill = skill, modifier = Modifier.fillMaxWidth())
            }
            Infos(
                label = "Contact",
                value = contact,
                modifier = Modifier.fillMaxWidth()
            )
            Action(
                text = actionLabel,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            ProfileHeader(
                name = name,
                role = role,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun Skill(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(bottom = 6.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun Infos(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 14.dp)) {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun Action(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(top = 18.dp)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ApprenticeProfile() {
    ProfilComposeTheme {
        MainScreen(
            name = "Melvin Didier",
            role = "Développeur Java/Angular",
            description = "Diplômé d'une licence Informatique, je m'oriente vers un Master en Architecture des logiciels en alternance chez Capgemini.",
            skills = listOf("Java", "Angular", "Python"),
            contact = "m.didier@myskolae.fr",
            actionLabel = "Contacter"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FootballerProfile() {
    ProfilComposeTheme {
        MainScreen(
            name = "Melvin Junior",
            role = "Footballeur professionnel",
            description = "Joueur professionnel au Celtic Glasgow depuis 1930, j'évolue au poste d'avant-centre.",
            skills = listOf("Buteur exceptionnel"),
            contact = "melvin.foot97@outlook.fr",
            actionLabel = "Voir les statistiques"
        )
    }
}
