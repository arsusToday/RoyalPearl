package com.snake.io.slit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.snake.io.slit.Const.jsoupCheck
import com.snake.io.slit.Const.linkFilterPart1
import com.snake.io.slit.Const.linkFilterPart2
import com.snake.io.slit.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var jsoup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        jsoup = ""
        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = coTask()
        }
        runBlocking {
            try {
                job.join()

                if (jsoup == jsoupCheck) {
                    Intent(applicationContext, InitGame::class.java).also { startActivity(it) }
                } else {
                    Intent(applicationContext, ApplicationClass::class.java).also { startActivity(it) }
                }
                finish()
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun coTask(): String {

        val forJsoupSetNaming =
            "${linkFilterPart1}${linkFilterPart2}"

        withContext(Dispatchers.IO) {
            getCodeFromUrl(forJsoupSetNaming)
        }
        return jsoup
    }

    private fun getCodeFromUrl(link: String) {
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                jsoup = text
            }
        } catch (ex: Exception) {

        } finally {
            urlConnection.disconnect()
        }
    }
}