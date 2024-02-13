package com.example.basicscodelab

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.Calendar


class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {

    // Let MyAppWidgetReceiver know which GlanceAppWidget to use
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()
}

class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

    // Load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
                MyContent()
        }
    }

    @Composable
    private fun MyContent() {

        val today = Calendar.getInstance()
        val dayOfWeek = today.get(Calendar.DAY_OF_WEEK)

        val dayOfWeekStr = when (dayOfWeek) {
            Calendar.SUNDAY -> "일"
            Calendar.MONDAY -> "월"
            Calendar.TUESDAY -> "화"
            Calendar.WEDNESDAY -> "수"
            Calendar.THURSDAY -> "목"
            Calendar.FRIDAY -> "금"
            Calendar.SATURDAY -> "토"
            else -> "알 수 없는 요일"
        }


        val days = listOf("월", "화", "수", "목", "금", "토", "일")
        Row (modifier = GlanceModifier.background(Color(0.3127f,0.3290f,0.3583f,0.5f)).fillMaxSize()){
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var bobData = remember {
                    mutableStateOf<JSONObject?>(null)
                }

                var cOrG = remember {
                    mutableStateOf("가좌캠")
                }

                var ready = remember {
                    mutableStateOf<Boolean>(false)
                }
                val setTime =remember {
                    mutableStateOf("lc")
                }
                    Thread {
                    bobData.value = JSONObject(getData());
                    ready.value=true;
                }.start()

                Row (modifier = GlanceModifier.fillMaxWidth()){
                    Text(text = "GNU Bob", modifier = GlanceModifier.defaultWeight().padding(12.dp),  style = TextStyle(textAlign = TextAlign.Center))
                }


                Row (modifier = GlanceModifier.fillMaxWidth()){
                    for(i in arrayOf("칠암캠", "가좌캠")){
                        if(i == cOrG.value){

                            Row(
                                modifier = GlanceModifier
                                    .clickable({cOrG.value=i})
                                    .background(Color(153, 102, 204)) // 여기에 원하는 배경색 설정
                                    .padding(8.dp)
                                    .cornerRadius(16.dp)
                            ) {
                                Text(
                                    text = "$i",
                                    style = TextStyle(textAlign = TextAlign.Center)
                                )
                            }

                        } else {

                            Row(
                                modifier = GlanceModifier
                                    .clickable({cOrG.value=i})
                                    .background(Color(204, 153, 255)) // 여기에 원하는 배경색 설정
                                    .padding(8.dp)
                                    .cornerRadius(16.dp)
                            ) {
                                Text(
                                    text = "$i",
                                    style = TextStyle(textAlign = TextAlign.Center)
                                )
                            }
                        }

                        Spacer(modifier = GlanceModifier.width(4.dp).height(4.dp))
                    }
                    Spacer(modifier = GlanceModifier.defaultWeight())
                    Button(text = "새로고침", onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            CoroutineScope(Dispatchers.IO).async {
                                bobData.value = JSONObject(getData());
                            }
                            ready.value=true;
                        }
                    })
                }
                Spacer(modifier = GlanceModifier.width(4.dp).height(4.dp))
                Row (modifier = GlanceModifier.fillMaxWidth()){
                    for(i in arrayOf(arrayOf("아침밥", "bf"), arrayOf("점심밥", "lc"),arrayOf("저녁밥", "dn"))){
                        if(i[1].equals(setTime.value)){
                            Row(
                                modifier = GlanceModifier
                                    .clickable({setTime.value="${i[1]}"})
                                    .background(Color(153, 102, 204)) // 여기에 원하는 배경색 설정
                                    .padding(8.dp)
                                    .cornerRadius(16.dp)
                            ) {
                                Text(
                                    text = "${i[0]}",
                                    style = TextStyle(textAlign = TextAlign.Center)
                                )
                            }
                        } else {
                            Row(
                                modifier = GlanceModifier
                                    .clickable({setTime.value="${i[1]}"})
                                    .background(Color(204, 153, 255)) // 여기에 원하는 배경색 설정
                                    .padding(8.dp)
                                    .cornerRadius(16.dp)
                            ) {
                                Text(
                                    text = "${i[0]}",
                                    style = TextStyle(textAlign = TextAlign.Center)
                                )
                            }
                        }
                        Spacer(modifier = GlanceModifier.width(4.dp).height(4.dp))
                    }


                }
                Spacer(modifier = GlanceModifier.width(4.dp).height(4.dp))
                Row(modifier = GlanceModifier.fillMaxWidth()) {

                    for(day in days){
//                        if(day==dayOfWeekStr){
//                            Text(text = "$day", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center, color = ColorProvider(Color.Yellow)))
//                        } else {
                            if(day == "일"){ Text(text = "$day", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center, color = ColorProvider(Color.Red)))}
                            else if(day == "토"){Text(text = "$day", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center, color = ColorProvider(Color(135, 206, 235))))}
                            else { Text(text = "$day", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center))}
//                        }
                    }
                }
                Row(modifier = GlanceModifier.fillMaxWidth()) {

                    for(day in days){
                        if(day==dayOfWeekStr){
                            Box(modifier = GlanceModifier.defaultWeight().background(ColorProvider(Color.Yellow)).padding(1.dp)){}
                        } else {
                            Box(modifier = GlanceModifier.defaultWeight()){}
                        }
                    }
                }

                if(ready.value){
                    if(cOrG.value == "가좌캠"){
                        for (time in arrayOf("bf", "lc", "dn")){
                            if(time == setTime.value){
                                Row(horizontalAlignment = Alignment.CenterHorizontally, modifier = GlanceModifier.fillMaxWidth()) {
                                    //요일별 밥 데이터 배열

                                    val  bobDataByTime = bobData.value?.getJSONObject("g")?.getJSONArray(time)

                                    println("sizeMode = ${bobDataByTime}")

                                    for (day in arrayOf(0,1,2,3,4,5,6)){
                                        //요일별 밥 데이터
                                        val bobDataByDay = bobDataByTime?.getJSONArray(day)
                                        var text = "";

                                        if(bobDataByDay?.length() != 0){

                                            for(i in 0..bobDataByDay!!.length() - 1) {
                                                text += "\n${bobDataByDay?.getString(i)}"
                                            }
                                            Text(text = "${text}", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center))
                                        } else {

                                            text = ""
                                            Text(text = "${text}", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center))
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (time in arrayOf("bf", "lc", "dn")){
                            if(time == setTime.value){
                                Row(horizontalAlignment = Alignment.CenterHorizontally, modifier = GlanceModifier.fillMaxWidth()) {
                                    //요일별 밥 데이터 배열
                                    val bobDataByTime = bobData.value?.getJSONObject("c")?.getJSONArray(time)
                                    for (day in arrayOf(0,1,2,3,4,5,6)){
                                        //요일별 밥 데이터
                                        val bobDataByDay = bobDataByTime?.getJSONArray(day)
                                        var text = "";

                                        if(bobDataByDay?.length() != 0){

                                            for(i in 0..bobDataByDay!!.length() - 1) {
                                                text += "\n${bobDataByDay?.getString(i)}"
                                            }
                                            Text(text = "${text}", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center))
                                        } else {

                                            text = ""
                                            Text(text = "${text}", modifier = GlanceModifier.defaultWeight(),  style = TextStyle(textAlign = TextAlign.Center))
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

fun getData() : String? {
        try {
            val url = "http://203.232.200.71:4005/";

            // OkHttp 클라이언트 객체 생성
            val client = OkHttpClient();
            // GET 요청 객체 생성
            val builder = Request.Builder().url(url).get();
            val request = builder.build();
            // OkHttp 클라이언트로 GET 요청 객체 전송
            val response = client.newCall(request).execute();
            if (response.isSuccessful) {
                // 응답 받아서 처리
                val body = response.body;
                return body?.string()
            }
            else{
                System.err.println("Error Occurred");
            }

        } catch(e : Exception) {
            e.printStackTrace();
        }
        return null
}



