package com.overengineered.sharenet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.overengineered.sharenet.databinding.FragmentSurplusItemsBinding

class SurplusItemsFragment : Fragment() {

    private var _binding: FragmentSurplusItemsBinding? = null
    private val binding get() = _binding!!
    private lateinit var surplusItemsAdapter: SurplusItemsAdapter
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSurplusItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        surplusItemsAdapter = SurplusItemsAdapter()
        binding.recyclerViewSurplusItems.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSurplusItems.adapter = surplusItemsAdapter

        loadSurplusItems()
    }

    private fun loadSurplusItems() {
        firestore.collection("surplus_items")
            .get()
            .addOnSuccessListener { result ->
                val items = result.map { document ->
                    document.toObject(SurplusItem::class.java)
                }
                surplusItemsAdapter.submitList(items)
            }
            .addOnFailureListener { exception ->
                println("App Crashed : Contact Developer")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
