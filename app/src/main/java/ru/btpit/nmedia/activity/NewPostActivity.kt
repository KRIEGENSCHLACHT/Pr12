package ru.btpit.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.btpit.nmedia.R
import ru.btpit.nmedia.databinding.ActivityNewPostBinding
import ru.btpit.nmedia.until.AndroidUtils
import ru.btpit.nmedia.until.StringArg
import ru.btpit.nmedia.viewmodel.PostViewModel
import ru.btpit.nmedia.activity.NewPostActivity.Companion.textArg


class NewPostActivity : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ActivityNewPostBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.textArg
            ?.let(binding.edit::setText)

        binding.ok.setOnClickListener {
            viewModel.changeContent(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityNewPostBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val text = intent?.getStringExtra(Intent.EXTRA_TEXT)
//        binding.edit.text = Editable.Factory.getInstance().newEditable(text)
//        if (text == "")
//        {
//            binding.Name.text = "Создание поста"
//        }
//        else binding.Name.text = "Редактирование поста"
//        binding.edit.requestFocus()
//        binding.ok.setOnClickListener {
//
//            val intent = Intent()
//            if (binding.edit.text.isNullOrBlank()) {
//                setResult(Activity.RESULT_CANCELED, intent)
//            } else {
//                val content = binding.edit.text.toString()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                setResult(Activity.RESULT_OK, intent)
//            }
//            finish()
//        }
//    }
}