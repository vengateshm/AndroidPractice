package com.android.vengateshm.androidpractice.dialog_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vengateshm.androidpractice.databinding.LayoutListDialogItemBinding

class ListDialogAdapter<T : DialogListable>(
    private val onItemSelected: ((T) -> Unit)?,
) : RecyclerView.Adapter<ListDialogAdapter.ListDialogItemViewHolder<T>>() {

    private var listItems = listOf<T>()

    fun setItems(list: List<T>) {
        listItems = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDialogItemViewHolder<T> {
        val binding =
            LayoutListDialogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListDialogItemViewHolder(binding, onItemSelected)
    }

    override fun onBindViewHolder(holder: ListDialogItemViewHolder<T>, position: Int) {
        holder.bindItem(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ListDialogItemViewHolder<T : DialogListable>(
        private val binding: LayoutListDialogItemBinding,
        private val onItemSelected: ((T) -> Unit)?,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(t: T) {
            binding.tvTitle.text = t.title()
            binding.root.setOnClickListener {
                onItemSelected?.invoke(t)
            }
        }
    }
}