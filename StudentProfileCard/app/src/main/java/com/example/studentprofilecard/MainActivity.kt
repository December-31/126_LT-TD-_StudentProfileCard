package com.example.studentprofilecard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilecard.databinding.ActivityMainBinding
import com.example.studentprofilecard.Model.Student // Nhập Data Class
import com.example.studentprofilecard.Utils.toAcademicRanking // Nhập Extension xếp loại
import com.example.studentprofilecard.Utils.toast // Nhập Extension thông báo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "22505120005",
        name = "Nguyen Van An",
        className = "DD2026",
        email = "anv@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()
            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent) // Vẽ lại dữ liệu mới lên Views

            toast("Cập nhật điểm thành công!")
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}