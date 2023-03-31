package com.setyo.mynoteappsroom.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setyo.mynoteappsroom.R
import com.setyo.mynoteappsroom.database.Note

class NoteAddUpdateActivity : AppCompatActivity() {

    private var isEdit = false
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add_update)
    }

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}