package com.sopt25.hackerthon.nagaraguel.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pickcocast.loginandboard.network.DothomeServiceImpl
import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import com.sopt25.hackerthon.nagaraguel.ui.main.MainActivity
import com.sopt25.hackerthon.nagaraguel.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var id_string : String
    private lateinit var pw_string : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }
    private fun init(){
        tv_register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent,100)
        }
        btn_login.setOnClickListener{
            id_string = et_id.text.toString()
            pw_string = et_pw.text.toString()
            if(id_string.equals("")||pw_string.equals("")){
                messageToastShow("빈칸 없이 입력해주세요")
                return@setOnClickListener
            }else{
                val dothomeService = DothomeServiceImpl.service.requestLogin(id_string,pw_string)
                dothomeService.enqueue(
                    object : Callback<ResponseValidateAndRegisterAndLogin> {
                        override fun onFailure(call: Call<ResponseValidateAndRegisterAndLogin>, t: Throwable) {
                            Log.d("hj","error : $t")
                        }
                        override fun onResponse(call: Call<ResponseValidateAndRegisterAndLogin>, response: Response<ResponseValidateAndRegisterAndLogin>) {
                            if(response.isSuccessful){
                                if(response.body()!!.success){
                                    messageToastShow("로그인 되었습니다.")
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    intent.putExtra("userID",id_string)
                                    intent.putExtra("userPassword",pw_string)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    messageToastShow("로그인 실패")
                                }
                            }
                        }
                    }
                )
            }
        }
    }
    //회원가입 하고 돌아왔을 때 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == 100
            -> {
                id_string = data?.getStringExtra("userID").toString()
                pw_string = data?.getStringExtra("userPassword").toString()
                if(id_string.equals("null")||pw_string.equals("null")){
                    return
                }else{
                    et_id.setText(id_string)
                    et_pw.setText(pw_string)
                }
            }
        }
    }
    private fun messageToastShow(message : String){
        val toast = Toast.makeText(this,message, Toast.LENGTH_SHORT)
        toast.show()
    }
}