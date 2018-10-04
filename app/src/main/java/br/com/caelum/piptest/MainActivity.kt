package br.com.caelum.piptest

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Rational
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carregaGif()


        fab.setOnClickListener {
            acionaPIP()
        }
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)

        if (isInPictureInPictureMode) {
            tiraFab()
        } else {
            colocaFab()

        }

    }

    override fun onUserLeaveHint() {
        if (!isInPictureInPictureMode) {
            acionaPIP()
        }
    }

    private fun carregaGif() {
        val url = "https://media.giphy.com/media/wsWM9TEHmXzUrs5lU3/giphy.gif"
        Glide.with(gifzinho)
                .asGif()
                .load(url)
                .into(gifzinho)
    }

    private fun acionaPIP() {
        val aspectRatio = Rational(gifzinho.width, gifzinho.height)

        val params = PictureInPictureParams.Builder().setAspectRatio(aspectRatio).build()

        enterPictureInPictureMode(params)
    }


    private fun colocaFab() {
        fab.visibility = VISIBLE
    }

    private fun tiraFab() {
        fab.visibility = GONE
    }


}
