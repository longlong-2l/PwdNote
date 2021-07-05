package com.surpassli.login.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.surpassli.login.R
import com.surpassli.login.data.model.User
import com.surpassli.login.databinding.ActivityLoginBinding

@Route(path = "/login/LoginActivity")
class LoginActivity : AppCompatActivity() {

    @Autowired(name = "path")
    @JvmField
    var mPath: String = ""

    private var binding: ActivityLoginBinding ?= null
    private var loggUser:User ?= null

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding?.user = User()
        ARouter.getInstance().inject(this)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            binding?.login?.isEnabled = loginState.isDataValid
            if (loginState.usernameError != null) {
                binding?.username?.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding?.password?.error = getString(loginState.passwordError)
            }
        })
        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer
            binding?.loading?.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
            finish()
        })
        binding?.username?.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding?.username?.text.toString(),
                binding?.password?.text.toString()
            )
        }
        binding?.password?.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding?.username?.text.toString(),
                    binding?.password?.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding?.username?.text.toString(),
                            binding?.password?.text.toString()
                        )
                }
                false
            }

            binding?.login?.setOnClickListener {
                binding?.loading?.visibility = View.VISIBLE
                loginViewModel.login(binding?.username?.text.toString(), binding?.password?.text.toString())
            }
        }
        loggUser = User()
        binding?.user = loggUser
        binding?.activity = this
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

//    public fun onMClick(view: View) {
//        val name = loggUser?.name
//        loggUser?.name = "name $name"
//    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}