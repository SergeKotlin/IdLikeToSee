package com.example.idliketosee.ui.screens.movieSelection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idliketosee.R

internal class MovieSelectionRecyclerAdapter( // В адаптере описывается способ связи между данными и компонентом
                                             //context: Context?,  //todo To delete (WORK ON MISTAKES)
                                             private val movies: List<String>,
                                             private val moviePic: Int,
                                             private val clickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieSelectionRecyclerAdapter.MovieSelectionViewHolder>(){

//    private val movies: List<String> // можно и тут
//    private val inflater: LayoutInflater //todo To delete (WORK ON MISTAKES)

    init {
//        inflater = LayoutInflater.from(context) //todo To delete (WORK ON MISTAKES)
    }

    inner class MovieSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), // Используемые компоненты из макета для отдельного элемента списка
    View.OnClickListener { // 1й вариант OnClickListener
        val movieImage: View
        val movieInfo: TextView

        init {
            movieImage = itemView.findViewById(R.id.movie_image)
            movieInfo = itemView.findViewById(R.id.movie_info)
            itemView.setOnClickListener(this) // 1й вариант OnClickListener
        }

        /*fun click() { //  2й вариант OnClickListener
            itemView.setOnClickListener { clickListener.onMovieClick(itemView, adapterPosition) }
        }*/

        override fun onClick(view: View?) { // 1й вариант OnClickListener
            clickListener.onMovieClick(view, adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSelectionViewHolder {
//        val itemView = inflater.inflate(R.layout.movie_selection_item, parent, false) //todo To delete (WORK ON MISTAKES)
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_selection_item, parent, false)
        return MovieSelectionViewHolder(itemView)
        /* Нужно указать id макета для отдельного элемента списка и вернуть объект класса ViewHolder.
         Если на экране можно отобразить 9 элементов списка, RecyclerView создаст 11-12 элементов (с запасом).
         Каким большим не был бы список - все данные будут размещаться в 11-12 элементах, менясь при прокрутке. */
    }

    override fun onBindViewHolder(holder: MovieSelectionViewHolder, position: Int) { // Связываем используемые текстовые метки с данными
        val infoOfMovie = movies[position]
        // holder.click() 2й вариант OnClickListener
        holder.movieImage.setBackgroundResource(moviePic)
        holder.movieInfo.text = infoOfMovie
        /* Параметр position отвечает за id в списке, по которому можно получить нужные данные. */
    }

    override fun getItemCount(): Int { // Возвращает количество элементов списка.
        return movies.size
        /* Адаптеру нужно знать, сколько элементов предоставить компоненту, чтобы распределить ресурсы
        и подготовиться к отображению на экране.
        Обычно данные однотипны, например, список строк. Можно просто вычислить и передать его длину. */
    }

    // TODO may Future
    interface MovieClickListener { // Пусть фрагмент реализует реакцию на клик
        fun onMovieClick(view: View?, position: Int)
    }
}

/* Как работают интерфейсы.. (thx Neko)
«Пульт» - интерфейс, «телик» - сложная система (логика фрагмента/активити),
«человек»(запускатель) - в данном случае RecyclerAdapter.
Пульт реализуется рядом с теликом (во фрагменте), человек (адаптер) также узнаёт о нём.
 */