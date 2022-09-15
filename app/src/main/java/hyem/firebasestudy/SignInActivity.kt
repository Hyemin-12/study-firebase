package hyem.firebasestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth
        val signInBtn = findViewById<Button>(R.id.sign_in_btn)

        signInBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()

            signInBtn.isEnabled = false

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 회원 가입 성공 로직
                        Toast.makeText(baseContext, "회원 가입 성공", Toast.LENGTH_SHORT).show()
                        // 이미 이 시점에 로그인 유저 정보가 auth의 currentUser 속성에 저장되므로 접근 가능
                        val user = auth.currentUser
                        Log.d("mytag", "회원 가입(=유저 생성) 성공 ${user.toString()}")
                        // 로그인 화면으로 돌아갈 수 있도록 finish
                        finish()
                    } else {
                        // 회원 가입 실패 로직 (task 객체를 통해서 exception 객체 접근 가능)
                        Log.w("mytag", "회원 가입 실패 (사유 : ${task.exception})",)
                        Toast.makeText(baseContext, "회원 가입 실패", Toast.LENGTH_SHORT).show()
                        signInBtn.isEnabled = true
                    }
                }
        }
    }
}