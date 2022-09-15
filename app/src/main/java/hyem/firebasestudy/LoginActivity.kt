package hyem.firebasestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null) {
            // 로그인된 사용자가 있는 경우 필요한 작업 진행 (ex: 그냥 로그인 화면 넘어가서 메인 화면으로 가기)
            startActivity(Intent(this, UserInfoActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.login_btn).setOnClickListener {
            val email = findViewById<EditText>(R.id.email_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // 로그인 성공
                        val user = auth.currentUser
                        Log.d("mytag", "로그인 성공 ${user.toString()}")
//                        startActivity(Intent(this, UserInfoActivity::class.java))
//                        finish()
                    } else {
                        // 로그인 실패
                        // If sign in fails, display a message to the user.
                        Log.d("mytag", "로그인 실패 (사유 : ${it.exception})")
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        findViewById<Button>(R.id.to_sign_in_btn).setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}