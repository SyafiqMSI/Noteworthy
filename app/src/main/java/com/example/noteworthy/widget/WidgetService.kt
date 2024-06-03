package com.example.noteworthy.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.example.noteworthy.miscellaneous.Constants

class WidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        val id = intent.getLongExtra(Constants.SelectedBaseNote, 0)
        return WidgetFactory(application, id)
    }
}