package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.ApolloClient as ApolloClient2
import com.apollographql.apollo3.ApolloClient as ApolloClient3
import com.apollographql.apollo.coroutines.await
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launch {
      val apolloClient2 = ApolloClient2.builder().serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql").build()

      val launches = apolloClient2.query(GetLaunchesQuery()).await()
      println("Launches retrieved with Apollo2:")
      println(launches.data)

      val apolloClient3 = ApolloClient3.Builder().serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql").build()

      val viewer = apolloClient3.query(GetViewerQuery()).execute()
      println("Viewer retrieved with Apollo3:")
      println(viewer.data)
    }
    setContent {
      MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Greeting("Android")
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyApplicationTheme {
    Greeting("Android")
  }
}