package com.example.basicscodelab


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Column {
        Text(text = "그누밥v1.0 - made.잠자는오리 / 24.02.07", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ) )
        Text(text = " - 칠암 식당 추가 및 최초 배포")
        Text(text = "그누밥v1.1 / 24.02.07", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 가좌 1식당 추가")
        Text(text = "그누밥v1.2 / 24.02.07", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 서버 로직 최적화")
        Text(text = "그누밥v1.3 / 24.02.08", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 위젯 디자인 수정, 서버 로직 최적화")
        Text(text = "그누밥v1.3.1 / 24.02.08", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 에러 수정")
        Text(text = "그누밥v1.3.2 / 24.02.08", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 에러 수정")
        Text(text = "그누밥v1.3.3 / 24.02.08", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 버그 수정")
        Text(text = "그누밥v1.3.4 / 24.02.10", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 버튼 Active상태 인식률 향상 및 css 수정")
        Text(text = " - 토, 일 텍스트 색깔 추가")
        Text(text = " - 오늘의 요일을 노란색 하이라이트로 표시 추가")

        Text(text = "그누밥v1.3.5 / 24.02.10", style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
        ))
        Text(text = " - 에러 수정")
    }

}
