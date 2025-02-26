package com.example.bookstore.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen()
{
    val auth=Firebase.auth
    val emailState= remember { mutableStateOf("") }
    val passwordState= remember { mutableStateOf("") }
    Log.d("MyLog","User email:${auth.currentUser?.email}")
    Column(modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center)
    {
        TextField(value = emailState.value, onValueChange = {
            emailState.value=it
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = passwordState.value, onValueChange = {
            passwordState.value=it
        })
        Button(onClick =
        {
            SignIn(auth,emailState.value,passwordState.value)
        }) {
            Text(text="Sign In")
        }
        Button(onClick =
        {
            SignUp(auth,emailState.value,passwordState.value)
        }) {
            Text(text="Sign Up")
        }
        Button(onClick =
        {
            SignOut(auth)
        }) {
            Text(text="Sign Out")
        }
    }
}
private fun SignUp(auth:FirebaseAuth,email:String,password:String){
    auth.createUserWithEmailAndPassword(email,password).
            addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d("MyLog","Sign Up successfull!")
                }
                else
                {
                    Log.d("MyLog","Sign Up failure!")
                }
            }
}
private fun SignIn(auth:FirebaseAuth,email:String,password:String){
    auth.signInWithEmailAndPassword(email,password).
    addOnCompleteListener {
        if(it.isSuccessful){
            Log.d("MyLog","Sign In successfull!")
        }
        else
        {
            Log.d("MyLog","Sign In failure!")
        }
    }
}

private fun SignOut(auth:FirebaseAuth){
    auth.signOut()
}