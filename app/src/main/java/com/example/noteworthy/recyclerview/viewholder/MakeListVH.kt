package com.example.noteworthy.recyclerview.viewholder

import android.util.TypedValue
import android.view.MotionEvent
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.noteworthy.databinding.RecyclerListItemBinding
import com.example.noteworthy.miscellaneous.setOnNextAction
import com.example.noteworthy.preferences.TextSize
import com.example.noteworthy.recyclerview.ListItemListener
import com.example.noteworthy.room.ListItem

class MakeListVH(
    val binding: RecyclerListItemBinding,
    listener: ListItemListener,
    touchHelper: ItemTouchHelper,
    textSize: String
) : RecyclerView.ViewHolder(binding.root) {

    init {
        val body = TextSize.getEditBodySize(textSize)
        binding.EditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, body)

        binding.EditText.setOnNextAction {
            listener.moveToNext(adapterPosition)
        }

        binding.EditText.doAfterTextChanged { text ->
            listener.textChanged(adapterPosition, requireNotNull(text).trim().toString())
        }

        binding.Delete.setOnClickListener {
            listener.delete(adapterPosition)
        }

        binding.CheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.EditText.isEnabled = !isChecked
            listener.checkedChanged(adapterPosition, isChecked)
        }

        binding.DragHandle.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                touchHelper.startDrag(this)
            }
            false
        }
    }

    fun bind(item: ListItem) {
        binding.root.reset()
        binding.EditText.setText(item.body)
        binding.CheckBox.isChecked = item.checked
    }
}