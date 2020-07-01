package Controller

import Services.AuthServices
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smackapp.R
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profileDefault"
    var avatarColor = "[0.5,0.5,0.5,1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        createSpinner.visibility = View.INVISIBLE
    }

    fun generateUserAvatar(view: View) {
        val random = Random()
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        if (color == 0) {
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }

        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(resourceId)

    }

    fun generateBgroundColorClicked(view: View) {
        val random = Random()
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        createAvatarImageView.setBackgroundColor(Color.rgb(r, g, b))

        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savedB = b.toDouble() / 255

        avatarColor = "[$savedR, $savedG, $savedB, 1]"
        println(avatarColor)
    }

    fun createUserbtnClicked(view: View) {
        enableSpinner(true)
        val userName = createUserNameTxt.text.toString()
        val email = createEmailText.text.toString()
        val password = createPasswordText.text.toString()
        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

            AuthServices.registerUser(this, email, password) { registerSuccess ->
                if (registerSuccess) {
                    AuthServices.loginUser(this, email, password) { loginSuccess ->
                        if (loginSuccess) {
                            AuthServices.createUser(
                                this,
                                userName,
                                email,
                                avatarColor,
                                userAvatar
                            ) { createSuccess ->
                                if (createSuccess) {

                                    enableSpinner(false)

                                    finish()

                                } else {
                                    errorToast()
                                }
                            }
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }

            }

        }else{
            Toast.makeText(this, "Please fill in user name, email, and password", Toast.LENGTH_LONG).show()
            enableSpinner(false)
        }


    }

    fun errorToast() {
        Toast.makeText(this, "Something went, wrong please try again.", Toast.LENGTH_LONG).show()
        enableSpinner(false)

    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            createSpinner.visibility = VISIBLE
        } else {
            createSpinner.visibility = View.INVISIBLE
        }
        createUserBttn.isEnabled = !enable
        createAvatarImageView.isEnabled = !enable
        backgroundColorBtn.isEnabled = !enable
    }
}

