package com.tonic.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.tonic.firebaseauth.api.ApiFunc
import com.tonic.firebaseauth.databinding.ActivityRegisterBinding
import com.tonic.firebaseauth.send.HttpAssemblyGetPara
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class RegisterActivity : AppCompatActivity() {
    private val mTAG = MainActivity::class.java.name
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener {
            Toast.makeText(this@RegisterActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val date = binding.editTextDate.text.toString()
            val count = binding.editTextCount.text.toString().toInt()
            callAPIAssembly(date,count)
        }
    }
    fun callAPIAssembly(date: String, count: Int) {
        Log.e(mTAG, "callAPILogin $date $count")
        val para = HttpAssemblyGetPara()
        para.x = date
        para.y = count
        ApiFunc().getAssembly(para, AssemblyCallback)
    }//login

    private var AssemblyCallback: Callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e(mTAG, "err msg = $e")
            //runOnUiThread(netErrRunnable)
        }

        @Throws(IOException::class)
        override fun onResponse(call: Call, response: Response) {
            Log.e(mTAG, "response=${response.body!!.string()}")
            //val res = ReceiveTransform.restoreToJsonStr(response.body!!.string())

//            runOnUiThread {
//                try {
//                    //val  rjUser: RJUser? = null
//                    //val  rjUser: RJUser = Gson().fromJson<Any>(res, RJUser::class.javaObjectType) as RJUser
//                    Log.e(mTAG, "res = $res")
//
//                    if (res != "Error" && !checkServerErrorString(res)) {
//
//                        val rjUser = Gson().fromJson<Any>(res, RJUser::class.java) as RJUser
//
//                        //if (rjUser.result.equals("0")) {
//                        if (rjUser.result == "0") {
//                            //fail
//                            //mLoadingView.setStatus(LoadingView.GONE)
//                            // Toast.makeText(mContext,rjUser.tc_zx104,Toast.LENGTH_LONG).show();
//                            //showMyToast(rjUser.tc_zx104, mContext)
//                            toast(rjUser.tc_zx104)
//
//                            val failIntent = Intent()
//                            failIntent.action = Constants.ACTION.ACTION_LOGIN_FAILED
//                            sendBroadcast(failIntent)
//                        } else {
//                            //success
//                            Log.e(mTAG, "loginCallback success")
//
//                            val successIntent = Intent()
//                            successIntent.action = Constants.ACTION.ACTION_LOGIN_SUCCESS
//                            sendBroadcast(successIntent)
//
//
//                            username = rjUser.tc_zx104
//
//
//                            Log.e(
//                                mTAG,
//                                "username = " + rjUser.tc_zx104 + " account = " + account + " password = " + password
//                            )
//                        }
//                    } else { //checkServerErrorString = true
//                        toast(getString(R.string.toast_server_error))
//
//                        val failIntent = Intent()
//                        failIntent.action = Constants.ACTION.ACTION_LOGIN_FAILED
//                        sendBroadcast(failIntent)
//                    }
//
//                }// try
//                catch (e: IOException) {
//                    //mLoadingView.setStatus(LoadingView.GONE)
//                    //Toast.makeText(mContext,getString(R.string.toast_server_error),Toast.LENGTH_LONG).show();
//                    //showMyToast(getString(R.string.toast_server_error), mContext)
//                    e.printStackTrace()
//
//                    val failIntent = Intent()
//                    failIntent.action = Constants.ACTION.ACTION_LOGIN_FAILED
//                    sendBroadcast(failIntent)
//
//                    runOnUiThread {
//
//                        toast(getString(R.string.toast_server_error))
//                    }
//                }
//            }
        }//response
    }
}