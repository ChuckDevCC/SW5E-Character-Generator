package com.chuckdevcc.sw5echaractergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.util.*
import androidx.lifecycle.Observer

private const val ARG_CHAR_ID = "crime_id"
private lateinit var character: CharacterSheet


class CharacterDisplayFragment : Fragment() {

    private val characterDetailViewModel: CharacterDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = CharacterSheet()
        val charId: UUID = arguments?.getSerializable(ARG_CHAR_ID) as UUID
        characterDetailViewModel.loadCharacter(charId)
        // load a character from the database
    }

    // display character from the database.
    companion object {
        fun newInstance(charId: UUID): CharacterDisplayFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CHAR_ID, charId)
            }
            return CharacterDisplayFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_display, container, false)

        return view
    }

}