package hyem.firebasestudy

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.*

data class City(
    val name: String? = null,
    val state: String? = null,
    val country: String? = null,
    @field:JvmField // use this annotation if your Boolean field is prefixed with 'is'
    val isCapital: Boolean? = null,
    val population: Long? = null,
    val regions: List<String>? = null
)

class MainActivity : AppCompatActivity() {
    val TAG = "mytag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        // where 사용해서 조건에 맞는 데이터들을 가져올 수 있음
        db.collection("cities")
//            .whereEqualTo("capital", true)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

//        // hashmap을 city로 형변환하고 싶을 때 toObject 사용
//        val docRef = db.collection("cities").document("BJ")
//        docRef.get().addOnSuccessListener { documentSnapshot ->
//            val city = documentSnapshot.toObject<City>()
//            Log.d(TAG, city.toString())
//        }

//        // 데이터 가져오기
//        // document를 정해주고 get()을 호출함
//        val docRef = db.collection("cities").document("SF")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }

//        val data = hashMapOf(
//            "name" to "Tokyo",
//            "country" to "Japan"
//        )
//
//        // document를 안주면 알아서 id를 만듦
//        db.collection("cities")
//            .add(data)
//            .addOnSuccessListener { documentReference ->
//                Log.d("mytag", "DocumentSnapshot written with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("mytag", "Error adding document", e)
//            }

//        val city = City("Los Angeles", "CA", "USA",
//            false, 5000000L, listOf("west_coast", "socal"))
//        db.collection("cities").document("LA").set(city)

//        val docData = hashMapOf(
//            "stringExample" to "Hello world!",
//            "booleanExample" to true,
//            "numberExample" to 3.14159265,
//            "dateExample" to Timestamp(Date()),
//            "listExample" to arrayListOf(1, 2, 3),
//            "nullExample" to null
//        )
//
//        val nestedData = hashMapOf(
//            "a" to 5,
//            "b" to true
//        )
//
//        docData["objectExample"] = nestedData
//
//        db.collection("data").document("one")
//            .set(docData)
//            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

//        val city = hashMapOf(
//            "name" to "Los Angeles",
//            "state" to "CA",
//            "country" to "USA"
//        )
//
//        db.collection("cities")
//            .document("LA")
//            .set(city)
//            .addOnSuccessListener {
//                Log.d("mytag", "DocumentSnapshot successfully written!")
//            }
//            .addOnFailureListener {
//                e -> Log.w("mytag", "Error writing document", e)
//            }
//
//        val student = hashMapOf(
//            "name" to "Choi Hyemin",
//            "age" to 18,
//            "class" to 3,
//            "gender" to "female"
//        )
//
//        db.collection("students")
//            .document("2312")
//            .set(student)
//            .addOnSuccessListener {
//                Log.d("mytag", "Success :)")
//            }
//            .addOnFailureListener {
//                e -> Log.w("mytag", "Falied :(", e)
//            }
    }
}