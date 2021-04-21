package com.chuckdevcc.sw5echaractergenerator

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class CharacterList : Fragment() {
    // Interface needed for hosting activities
    interface Callbacks {
        fun onCharacterSelected(CharId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var characterRecyclerView: RecyclerView
    private var adapter: CharacterAdapter? = null
    private val CharacterListViewModel: CharacterListViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
    }

    // make the current fragment a stored variable
    // we can return to.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newInstance(): CharacterList {
            return CharacterList()
        }
    }

    //when the controller makes the view model,
    // create a recycler view.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_characters, container, false)

        characterRecyclerView =
            view.findViewById(R.id.character_recycler_view) as RecyclerView
        characterRecyclerView.layoutManager = LinearLayoutManager(context)
        characterRecyclerView.adapter = adapter
        return view
    }

    //display all the characters from the list of character sheets
    private fun updateUI(characterSheet: List<CharacterSheet>) {
        adapter = CharacterAdapter(characterSheet)
        characterRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CharacterListViewModel.CharacterListLiveData.observe(
            viewLifecycleOwner,
            Observer { CharacterSheet ->
                CharacterSheet?.let {
                    updateUI(CharacterSheet)
                }
            }
        )
    }

    // when we press the back button,
    // return to previous fragments.
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class CharacterHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var character: CharacterSheet
        private val characterNameTextView: TextView = itemView.findViewById(R.id.character_name)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(character: CharacterSheet) {
            this.character = character
            characterNameTextView.text = this.character.name
            View.VISIBLE
        }

        override fun onClick(v: View?) {
            callbacks?.onCharacterSelected(character.id)
        }

    }


    private inner class CharacterAdapter(var characters: List<CharacterSheet>) :
        RecyclerView.Adapter<CharacterHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
            val view = layoutInflater.inflate(R.layout.list_item_character, parent, false)
            return CharacterHolder(view)
        }

        override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
            val character = characters[position]
            holder.bind(character)
        }

        override fun getItemCount() = characters.size

    }
}