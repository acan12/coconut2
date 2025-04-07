package app.coconut2.sample.domain.usecase

import java.util.Locale
import javax.inject.Inject

class GetUserUseCase @Inject constructor() {
    operator fun invoke(names: List<String>?): String? =
        names?.find { it == Locale.getDefault().language }
}