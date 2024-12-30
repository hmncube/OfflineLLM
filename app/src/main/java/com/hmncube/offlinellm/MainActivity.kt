package com.hmncube.offlinellm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import com.hmncube.offlinellm.ui.theme.OfflineLLMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Set the configuration options for the LLM Inference task
        val options = LlmInference.LlmInferenceOptions.builder()
            .setModelPath("/data/local/.../")
            .setMaxTokens(1000)
            //.setMaxTopK(40)
            //.setTemperature(0.8)
            //.setRandomSeed(101)
            .build()


        val llmInference = LlmInference.createFromOptions(this, options)

        val answer = llmInference.generateResponse("What is the capital city of Zimbabwe?")
        setContent {
            OfflineLLMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = answer,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OfflineLLMTheme {
        Greeting("Android")
    }
}