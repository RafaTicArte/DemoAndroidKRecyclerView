package com.ticarte.rafa.demoandroidkrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 * El Adaptador almacenará los datos y creará una vista para ellos
 */
class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    /**
     * El ViewHolder es una clase interna al Adaptador
     * que se encarga de renderizar la vista de cada elemento
     */
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookTitle = view.findViewById(R.id.titleTextView) as TextView
        val bookYear = view.findViewById(R.id.yearTextView) as TextView

        fun bind(book: Book, context: Context){
            bookTitle.text = book.title
            bookYear.text = book.year.toString()
            // @TODO: mejorar el listener
            // https://medium.com/@amsavarthan/the-modern-approach-to-handle-item-click-on-recyclerview-6292cca3178d
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, book.title, Toast.LENGTH_SHORT).show()
            })
        }
    }

    var books: MutableList<Book>  = ArrayList()
    lateinit var context: Context
    lateinit var listener: View.OnClickListener;

    // Contruye el objeto adaptador recibiendo la lista de datos
    fun BookAdapter(books : MutableList<Book>, context: Context){
        this.books = books
        this.context = context
    }

    // Crea los nuevos objetos ViewHolder necesarios para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto BookViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookViewHolder(layoutInflater.inflate(R.layout.book_item, parent, false))
    }

    // Actualiza los datos del ViewHolder ya existente
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = books.get(position)
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return books.size
    }
}