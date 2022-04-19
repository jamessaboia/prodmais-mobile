package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jamessaboia.prodmaisapp.Model.Notes
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.ViewModel.NotesViewModel
import com.jamessaboia.prodmaisapp.databinding.FragmentEditNotesBinding

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding

    //  var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtNotes.setText(oldNotes.data.notes)


//        when (oldNotes.data.priority) {
//            "1" -> {
//                  priority = "1"
//                  binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//                  binding.pYellow.setImageResource(0)
//                  binding.pRed.setImageResource(0)
//            }
//            "2" -> {
//                  priority = "2"
//                  binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
//                  binding.pGreen.setImageResource(0)
//                  binding.pRed.setImageResource(0
//            }
//            "3" -> {
//                  priority = "3"
//                  binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
//                  binding.pGreen.setImageResource(0)
//                  binding.pYellow.setImageResource(0)
//            }
//        }


//        binding.pGreen.setOnClickListener {
//            priority = "1"
//            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pYellow.setImageResource(0)
//            binding.pRed.setImageResource(0)
//        }
//        binding.pYellow.setOnClickListener {
//            priority = "2"
//            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pRed.setImageResource(0)
//        }
//        binding.pRed.setOnClickListener {
//            priority = "3"
//            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pYellow.setImageResource(0)
//        }

        binding.btnEditSaveNotes.setOnClickListener {

            updateNotes(it)

        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val data = Notes(
            oldNotes.data.id,
            title = title,
            notes = notes
        ) //adicionar " priority " dentro do paramentro se quiseres por de volta esta feature

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Tarefa Editada com Sucesso!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottonSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottonSheet.setContentView(R.layout.dialog_delete)

            bottonSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}