package il.ac.afeka.cloud.rsocketmessagesmicroservice.utils

class InputValidation {
    companion object {
        fun isValidEmail(email: String?): Boolean {
            if (email.isNullOrBlank()) {
                return false
            }
            val emailReg = "^[a-zA-Z]+[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
            val pattern = Regex(emailReg)
            return pattern.matches(email) // Returns true if the email is valid, false if invalid
        }


        fun isValidTitle(title: String?) : Boolean {
            return title != null
        }
    }
}