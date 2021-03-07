package k.example.ktloginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import k.example.ktloginsignup.data.UserPreferences
import k.example.ktloginsignup.ui.auth.AuthActivity
import k.example.ktloginsignup.ui.home.HomeActivity
import k.example.ktloginsignup.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this,AuthActivity::class.java))
        finish()

        val userPreferences=UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            //Toast.makeText(this, it ?: "null", Toast.LENGTH_SHORT).show()

            val activity=if(it==null)AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}