package com.sopt25.hackerthon.nagaraguel.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pickcocast.loginandboard.network.DothomeServiceImpl
import com.pickcocast.loginandboard.network.request.validate.RequestValidation
import com.pickcocast.loginandboard.network.request.validate.RequestValidationInterface
import com.sopt25.hackerthon.nagaraguel.R
import com.sopt25.hackerthon.nagaraguel.data.ResponseValidateAndRegisterAndLogin
import com.sopt25.hackerthon.nagaraguel.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private var validate : Boolean = false
    val dothomeServiceImpl = DothomeServiceImpl
    private val requestValidation : RequestValidationInterface =
        RequestValidation()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }
    private fun init(){


        //아이디 중복확인
        btn_validate.setOnClickListener{
            val id_string = et_id.text.toString()
            if(id_string.equals("")){
                messageToastShow("아이디는 빈칸일 수 없습니다.")
                return@setOnClickListener
            }else{
                //아이디 중복검사 요청. -> 이상없으면 validate true로, 버튼과 아이디 비활성화
                requestValidation.requestValidate(id_string).enqueue(
                    object : Callback<ResponseValidateAndRegisterAndLogin> {
                        override fun onResponse(call: Call<ResponseValidateAndRegisterAndLogin>, response: Response<ResponseValidateAndRegisterAndLogin>) {
                            if(response.isSuccessful){//네트워크 통신 성공했을 때.
                                validate  = response.body()!!.success
                                if(validate){
                                    messageToastShow("사용할 수 있는 아이디입니다.")
                                    et_id.isEnabled = false
                                    btn_validate.isEnabled = false
                                }else{
                                    messageToastShow("중복된 아이디 입니다.")
                                }
                            }
                        }
                        override fun onFailure(call: Call<ResponseValidateAndRegisterAndLogin>, t: Throwable) {
                            Log.d("hj","error: $t")
                        }
                    }
                )
            }
        }

        //회원가입 버튼
        btn_register.setOnClickListener{
            val id_string = et_id.text.toString()
            val pw_string = et_pw.text.toString()
            val phone_string = et_phone.text.toString()
            if(!validate){//중복확인이 안된 경우
                messageToastShow("중복 체크를 해주세요.")
                return@setOnClickListener
            }else if(id_string.equals("")||pw_string.equals("")||phone_string.equals("")){
                messageToastShow("빈칸 없이 작성해주세요")
                return@setOnClickListener
            }else{
                //이상 없으니 회원 등록 실시.
                val dothomeService = dothomeServiceImpl.service.requestRegister(id_string,pw_string,phone_string)
                dothomeService.enqueue(
                    object : Callback<ResponseValidateAndRegisterAndLogin> {
                        override fun onFailure(
                            call: Call<ResponseValidateAndRegisterAndLogin>,
                            t: Throwable
                        ) {

                        }

                        override fun onResponse(call: Call<ResponseValidateAndRegisterAndLogin>, response: Response<ResponseValidateAndRegisterAndLogin>) {
                            if(response.isSuccessful) {
                                if (response.body()!!.success) {
                                    messageToastShow("회원가입 되었습니다")
                                    val intent: Intent =
                                        Intent(this@RegisterActivity, LoginActivity::class.java)
                                    intent.putExtra("userID", id_string)
                                    intent.putExtra("userPassword", pw_string)
                                    setResult(100, intent)
                                    finish()
                                } else {
                                    messageToastShow("회원가입 실패")
                                }
                            }
                        }

                    }
                )
            }
        }

    }
    private fun messageToastShow(message : String){
        val toast = Toast.makeText(this,message, Toast.LENGTH_SHORT)
        toast.show()
    }
}
