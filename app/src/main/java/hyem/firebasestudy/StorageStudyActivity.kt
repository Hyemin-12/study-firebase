package hyem.firebasestudy

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

class StorageStudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_study)

        // storage -> 하드디스크 같은 역할
        val storage = Firebase.storage
        var storageRef = storage.reference

        val ref = storageRef.child("hello.txt")
        // 내부 저장소에 파일 생성 후 저장하기 위해서 파일 객체 만들기
        // (Device File Explorer의 data/data/디폴트패키지이름/files 폴더 내부에서 확인 가능)
        val localFile = File(filesDir, "hello.txt")
        // getFile 호출 이후 다운로드 시작
        // getFile에 파일 정보를 저장해야 함
        val downloadTask = ref.getFile(localFile)
        // 반환받은 Task 객체를 통해서 콜백 메서드 등록 가능
        downloadTask.addOnSuccessListener {
            // 성공 후 로직
            Log.d("mytag", "다운로드 성공 (총 다운로드 바이트 : ${it.totalByteCount})")
            val bytes = localFile.readBytes()
            for(b in bytes) Log.d("mytag", b.toString())
        }.addOnFailureListener {
            // 실패 로직
            Log.d("mytag", "다운로드 실패 ${it.message}")
        }

//        // 파일 경로 전달
//        val fileRef = storageRef.child("hello/world/hello.txt")
//        // 업로드할 바이트 내용 구성
//        // 파일은 거의 대부분 byte 배열로 받음
//        val bytes = byteArrayOf(72, 101, 108, 108, 111)
//        // 업로드 작업 진행 (실제 업로드가 진행되며 업로드 결과에 대한 콜백 함수 설정 위해서 반환받은 Task 객체에 접근 가능)
//        val uploadTask = fileRef.putBytes(bytes)
//
//        uploadTask.addOnFailureListener {
//            Log.d("mytag", "업로드 실패")
//        }.addOnSuccessListener {
//            // 파일 업로드 성공 이후 메타데이터 객체에 접근하여 파일 관련 데이터 접근 가능
//            val metadata = it.metadata
//            // 파일 이름
//            Log.d("mytag", metadata?.name.toString())
//            // 파일 경로 + 파일 이름
//            Log.d("mytag", metadata?.path.toString())
//            // 파일 크기(바이트)
//            Log.d("mytag", metadata?.sizeBytes.toString())
//            // MIME 타입
//            Log.d("mytag", metadata?.contentType.toString())
//            // 생성 시간, 수정 시간
//            Log.d("mytag", metadata?.creationTimeMillis.toString())
//            Log.d("mytag", metadata?.updatedTimeMillis.toString())
//        }
    }
}