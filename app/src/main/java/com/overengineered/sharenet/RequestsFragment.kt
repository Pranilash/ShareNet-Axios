package com.overengineered.sharenet


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.overengineered.sharenet.databinding.FragmentRequestsBinding
import androidx.recyclerview.widget.RecyclerView


class RequestsFragment : Fragment() {

    private var _binding: FragmentRequestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var requestsAdapter: RequestsAdapter
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestsAdapter = RequestsAdapter()
        binding.recyclerViewRequests.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRequests.adapter = requestsAdapter

        loadRequests()
    }

    private fun loadRequests() {
        firestore.collection("requests")
            .get()
            .addOnSuccessListener { result ->
                val requests = result.map { document ->
                    document.toObject(Request::class.java)
                }
                requestsAdapter.submitList(requests)
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
