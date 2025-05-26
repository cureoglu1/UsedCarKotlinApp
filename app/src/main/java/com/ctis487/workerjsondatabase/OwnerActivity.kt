//package com.ctis487.workerjsondatabase
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.lifecycle.Observer
//import androidx.work.Data
//import androidx.work.OneTimeWorkRequest
//import androidx.work.WorkInfo
//import androidx.work.WorkManager
//import com.ctis487.workerjsondatabase.backgroundservice.CustomWorker
//import com.ctis487.workerjsondatabase.databinding.ActivityOwnerBinding
//import com.ctis487.workerjsondatabase.db.DbViewModel
//import com.ctis487.workerjsondatabase.util.Utils
//import com.google.android.material.snackbar.Snackbar
//
//class OwnerActivity : AppCompatActivity() {
//    lateinit var bindingOwner: ActivityOwnerBinding
//
//    lateinit var workManager: WorkManager
//    lateinit var workRequest: OneTimeWorkRequest
//    lateinit var customWorker: CustomWorker
//    lateinit var dbViewModel: DbViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        bindingOwner = ActivityOwnerBinding.inflate(layoutInflater)
//        setContentView(bindingOwner.root)
//
//        ViewCompat.setOnApplyWindowInsetsListener(bindingOwner.main) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java)
//            .setInputData(
//                Data.Builder().putString("ownerName", Utils.parent.owner.name)
//                    .putString("ownerDate", Utils.parent.owner.date).build()
//            )
//            .build()
//
//        workManager = WorkManager.getInstance(applicationContext)
//
//        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this@OwnerActivity,
//            Observer{ workInfo ->
//                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
//                    val resultData: Data = workInfo.outputData
//                    Snackbar.make(bindingOwner.btnSaveToDatabase, "SUCCEEDED " + resultData.getString("result"), Snackbar.LENGTH_LONG ).show()
//                }
//            })
//
//        bindingOwner.tvOwnerDetail.text= Utils.parent.owner.toString()
//        bindingOwner.btnSaveToDatabase.setOnClickListener {
//            workManager.enqueue(workRequest)
//        }
//    }
//}