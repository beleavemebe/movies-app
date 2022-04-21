package com.github.beleavemebe.moviesapp.presentation.reminders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentRemindersBinding

class RemindersFragment : Fragment(R.layout.fragment_reminders) {
    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRemindersBinding.bind(view)
    }
}
