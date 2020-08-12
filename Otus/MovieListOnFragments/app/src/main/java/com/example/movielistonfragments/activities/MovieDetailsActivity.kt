package com.example.movielistonfragments.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.movielistonfragments.R
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object{
        const val TAG_1: String = "COMMENT_CHECK_BOX"
        const val TAG_2: String = "COMMENT_TEXT"
    }

    lateinit var checkBox: CheckBox
    lateinit var textForCheckBox: TextView
    lateinit var textComment: EditText
    lateinit var commitButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        image_movie_details.setImageResource((intent.getStringExtra("MovieImage")).toInt())
        movie_name_details.text = intent.getStringExtra("MovieName")
        movie_description_details.text = intent.getStringExtra("MovieFullDescription")

        doCheckBox()
        commit()
    }

    private fun doCheckBox()  {
        checkBox = findViewById(R.id.check_box)
        textForCheckBox = findViewById(R.id.text_for_check_box)
        textComment = findViewById(R.id.text_comment)
        checkBox.setOnClickListener{
            if(checkBox.isChecked) {
                textForCheckBox.setVisibility(View.INVISIBLE)
                textComment.setVisibility(View.VISIBLE)
                Log.i(TAG_1, checkBox.isChecked.toString())
            }else{
                textForCheckBox.setVisibility(View.VISIBLE)
            }}
    }

    private fun commit() {
        commitButton = findViewById(R.id.commit_and_back_button)
        commitButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "You comment ''${textComment.text.toString()}'' was commited", Toast.LENGTH_LONG).show()
            Log.i(TAG_2, textComment.text.toString())
            startActivity(intent)
        }
    }

}