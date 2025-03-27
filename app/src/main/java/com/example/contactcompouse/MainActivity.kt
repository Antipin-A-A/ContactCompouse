package com.example.contactcompouse

import android.R.attr.fontFamily
import android.R.attr.fontStyle
import android.R.attr.letterSpacing
import android.R.attr.lineHeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactInfo()
        }
    }


    @Composable
    fun ContactDetails(contact: Contact) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImagePreview(contact)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    text = "${contact.name} ${contact.surname.orEmpty()}"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineMedium,
                        text = contact.familyName
                    )
                    if (contact.isFavorite) Image(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = android.R.drawable.star_on),
                        contentDescription = null
                    )
                }


            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1F)
            ) {
                InfoRow("Телефон:  ", contact.phone)
                InfoRow("Адрес:  ", contact.address)
                if (contact.email != null) {
                    InfoRow("E-mail:  ", contact.email)
                }
            }
        }

    }

    @Composable
    fun InfoRow(text: String, contact: String?) {
        Row(
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 8.dp,
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.Right,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = contact.toString(),
                modifier = Modifier.weight(0.5f)

            )
        }
    }


    @Composable
    fun ImagePreview(contact: Contact) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (contact.imageRes == null) {
                    Image(
                        painter = painterResource(id = R.drawable.circle),
                        contentDescription = null
                    )
                    Text(text = "${contact.name?.take(1)}${contact.familyName.take(1)}")
                } else {
                    Image(
                        painter = painterResource(id = contact.imageRes),
                        contentDescription = null
                    )

                }
            }
        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactInfo() {
        ContactDetails(
            Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                imageRes = null/*R.drawable.nia_480*/,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г.Москва, 3-я улица Строителей, д.25,кв.12",
                email = "fffff@gggg"
            )
        )
    }
}

