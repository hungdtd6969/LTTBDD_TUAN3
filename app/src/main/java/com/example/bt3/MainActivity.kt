package com.example.bt3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt3.ui.theme.Bt3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bt3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var isReady by remember { mutableStateOf(false) }
                    if (!isReady) {
                        ComposeTutorialScreen(
                            onNavigateToList = { isReady = true },
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        ListScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }
}

// 1. MÀN HÌNH GIỚI THIỆU (HOME)
@Composable
fun ComposeTutorialScreen(
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.compose_logo)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(bottom = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = image,
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(160.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Jetpack Compose",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            fontSize = 14.sp,
            color = Color(0xFF555555),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.weight(1.6f))
        Button(
            onClick = { onNavigateToList() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(52.dp)
        ) {
            Text(text = "I'm ready", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

// 2. MÀN HÌNH DANH SÁCH (CÓ ĐỦ CÁC MỤC MỚI)
@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf<String?>(null) }

    if (selectedItem == null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "UI Components List",
                color = Color(0xFF298AE8),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
            )

            // Nhóm Display
            ItemTitle("Display")
            ItemCard(title = "Text", desc = "Displays text", onClick = { selectedItem = "Text" })
            ItemCard(title = "Image", desc = "Displays an image", onClick = { selectedItem = "Image" })

            // Nhóm Input
            ItemTitle("Input")
            ItemCard(title = "TextField", desc = "Input field for text", onClick = { selectedItem = "TextField" })
            ItemCard(title = "PasswordField", desc = "Input field for passwords", onClick = { selectedItem = "PasswordField" })
            ItemCard(title = "Slider", desc = "Selects a value from a range", onClick = { selectedItem = "Slider" })

            // Nhóm Selection
            ItemTitle("Selection")
            ItemCard(title = "Button", desc = "Triggers an action when tapped", onClick = { selectedItem = "Button" })

            // Nhóm Layout
            ItemTitle("Layout")
            ItemCard(title = "Column", desc = "Arranges elements vertically", onClick = { selectedItem = "Column" })
            ItemCard(title = "Row", desc = "Arranges elements horizontally", onClick = { selectedItem = "Row" })


        }
    } else {
        when (selectedItem) {
            "Text" -> TextDetailScreen(onBack = { selectedItem = null })
            "Image" -> ImageDetailScreen(onBack = { selectedItem = null })
            "TextField" -> TextFieldDetailScreen(onBack = { selectedItem = null })
            "PasswordField" -> PasswordFieldDetailScreen(onBack = { selectedItem = null })
            "Slider" -> SliderScreen(onBack = { selectedItem = null })
            "Button" -> ButtonScreen(onBack = { selectedItem = null })
            "Column" -> ColumnDetailScreen(onBack = { selectedItem = null })
            "Row" -> RowDetailScreen(onBack = { selectedItem = null })
        }
    }
}

@Composable
fun ItemCard(
    title: String,
    desc: String,
    bgColor: Color = Color(0xFFB8D5FA),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = desc, fontSize = 13.sp, color = Color(0xFF333333))
        }
    }
}

@Composable
fun ItemTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black,
        modifier = Modifier.padding(top = 14.dp, bottom = 6.dp)
    )
}

@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Text(text = "<", fontSize = 26.sp, color = Color(0xFF298AE8), fontWeight = FontWeight.Bold)
        }
        Text(
            text = title,
            color = Color(0xFF298AE8),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f).padding(end = 48.dp)
        )
    }
}

// 3. MÀN HÌNH: TEXT DETAIL
@Composable
fun TextDetailScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        TopBar(title = "Text Detail", onBack = onBack)

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val styledText = buildAnnotatedString {
                append("The ")
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("quick ")
                }
                withStyle(SpanStyle(color = Color(0xFFB45309), fontSize = 28.sp, fontWeight = FontWeight.Bold)) {
                    append("Brown\n")
                }
                append("fox ")
                withStyle(SpanStyle(letterSpacing = 4.sp)) {
                    append("j u m p s ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("over\n")
                }
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("the ")
                }
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("lazy")
                }
                append(" dog.")
            }
            Text(text = styledText, fontSize = 22.sp, lineHeight = 36.sp)
        }
    }
}

// ==========================================
// 4. MÀN HÌNH: IMAGES
// ==========================================
@Composable
fun ImageDetailScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "Images", onBack = onBack)

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(R.drawable.compose_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )
        Text(
            text = "https://giaothongvantaihcm.edu.vn/.../Logo-GTVT.png",
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(R.drawable.compose_logo),
            contentDescription = "In app",
            modifier = Modifier.size(150.dp)
        )
        Text(text = "In app", color = Color.Gray, fontSize = 12.sp)
    }
}

// 5. MÀN HÌNH: TEXTFIELD
@Composable
fun TextFieldDetailScreen(onBack: () -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "TextField", onBack = onBack)

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("Thông tin nhập", color = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (inputText.isEmpty()) "Tự động cập nhật dữ liệu theo textfield" else inputText,
            color = if (inputText.isEmpty()) Color(0xFFDC2626) else Color.Black,
            fontSize = 14.sp
        )
    }
}

// 6. MÀN HÌNH: PASSWORD FIELD
@Composable
fun PasswordFieldDetailScreen(onBack: () -> Unit) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "PassWordField", onBack = onBack)

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("PassWord") },
            placeholder = { Text("Thông tin nhập", color = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                val description = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        )
    }
}
// 7. MÀN HÌNH: SLIDER
@Composable
fun SliderScreen(onBack: () -> Unit) {
    var sliderValue by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "Slider", onBack = onBack)

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Giá trị: ${sliderValue.toInt()}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        )
    }
}

// 8. MÀN HÌNH: BUTTON
@Composable
fun ButtonScreen(onBack: () -> Unit) {
    // Biến ghi nhớ nút nào vừa được bấm
    var clickedButton by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "Button", onBack = onBack)

        Spacer(modifier = Modifier.height(20.dp))

        // Hiển thị highlight thông báo nút vừa được bấm
        if (clickedButton != null) {
            Text(
                text = "Bạn vừa bấm: $clickedButton",
                color = Color(0xFF1D4ED8),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier
                    .background(Color(0xFFE0EDFF), RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 1. Button
        Button(
            onClick = { clickedButton = "Button" },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (clickedButton == "Button") Color(0xFF1D4ED8) else Color(0xFF4C5D8A)
            )
        ) {
            Text(
                text = "Here",
                color = if (clickedButton == "Button") Color.Yellow else Color.White,
                fontWeight = if (clickedButton == "Button") FontWeight.Bold else FontWeight.Normal
            )
        }
        Text(text = "Button", fontSize = 13.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(16.dp))

        ElevatedButton(
            onClick = { clickedButton = "ElevatedButton" }
        ) {
            Text(
                text = "Here",
                color = if (clickedButton == "ElevatedButton") Color(0xFF1D4ED8) else Color(0xFF4C5D8A),
                fontWeight = if (clickedButton == "ElevatedButton") FontWeight.Bold else FontWeight.Normal
            )
        }
        Text(text = "ElevatedButton", fontSize = 13.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(16.dp))

        FilledIconButton(
            onClick = { clickedButton = "FilledTonalButton" }
        ) {
            Text(
                text = "Here",
                fontSize = 12.sp,
                color = if (clickedButton == "FilledTonalButton") Color.Yellow else Color.White,
                fontWeight = if (clickedButton == "FilledTonalButton") FontWeight.Bold else FontWeight.Normal
            )
        }
        Text(text = "FilledTonalButton", fontSize = 13.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { clickedButton = "OutlinedButton" }
        ) {
            Text(
                text = "Here",
                color = if (clickedButton == "OutlinedButton") Color(0xFF1D4ED8) else Color(0xFF4C5D8A),
                fontWeight = if (clickedButton == "OutlinedButton") FontWeight.Bold else FontWeight.Normal
            )
        }
        Text(text = "OutlinedButton", fontSize = 13.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(16.dp))

        // 5. TextButton
        TextButton(
            onClick = { clickedButton = "TextButton" }
        ) {
            Text(
                text = "Here",
                color = if (clickedButton == "TextButton") Color(0xFFDC2626) else Color(0xFF1D4ED8),
                fontWeight = if (clickedButton == "TextButton") FontWeight.Bold else FontWeight.Normal,
                textDecoration = if (clickedButton == "TextButton") TextDecoration.Underline else TextDecoration.None
            )
        }
        Text(text = "TextButton", fontSize = 13.sp, color = Color.DarkGray)
    }
}

// 9. MÀN HÌNH: COLUMN LAYOUT
@Composable
fun ColumnDetailScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "Column Layout", onBack = onBack)

        repeat(4) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF93C5FD), RoundedCornerShape(8.dp))
            )
        }
    }
}

// 10. MÀN HÌNH: ROW LAYOUT
@Composable
fun RowDetailScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopBar(title = "Row Layout", onBack = onBack)

        repeat(4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f).height(60.dp).background(Color(0xFF93C5FD), RoundedCornerShape(8.dp)))
                Box(modifier = Modifier.weight(1f).height(60.dp).background(Color(0xFF3B82F6), RoundedCornerShape(8.dp)))
                Box(modifier = Modifier.weight(1f).height(60.dp).background(Color(0xFF93C5FD), RoundedCornerShape(8.dp)))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeTutorialScreenPreview() {
    Bt3Theme {
        ComposeTutorialScreen(onNavigateToList = {})
    }
}