package com.skyeng.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.skyeng.R
import com.skyeng.databinding.SearchResultListTextBinding
import com.skyeng.databinding.SearchResultListTranslationBinding
import com.skyeng.model.SearchResult

class SearchResultListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TextViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    class TranslationViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    enum class ItemType {
        TEXT,
        TRANSLATION
    }

    class Item(val content: String, val type: ItemType)

    private val items = mutableListOf<Item>()

    fun setData(data: List<SearchResult>) {
        items.clear()
        for (result in data) {
            items.add(Item(result.text, ItemType.TEXT))

            for (meaning in result.meanings) {
                items.add(Item(meaning.translation.text, ItemType.TRANSLATION))
            }
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ItemType.TEXT.ordinal ->
                TextViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.search_result_list_text, parent, false))
            else ->
                TranslationViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.search_result_list_translation, parent, false))
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder ->
                (holder.binding as SearchResultListTextBinding).content = items[position].content
            is TranslationViewHolder ->
                (holder.binding as SearchResultListTranslationBinding).apply {
                    content = items[position].content
                    root.setOnClickListener {  }
                }
        }
    }
}