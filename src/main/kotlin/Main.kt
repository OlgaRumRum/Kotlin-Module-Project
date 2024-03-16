import java.util.InputMismatchException
import java.util.Scanner

fun main(args: Array<String>) {
    println("Приложение Заметки приветствует Вас!")
    val notesApp = NotesApp()
    val scanner = Scanner(System.`in`)
    while (true) {
        try {
            println("Выберите пункт меню\n0. Создать архив\n1. Список архивов\n2. Выход")
            val command = scanner.nextInt()
            when (command) {
                0 -> notesApp.createArchive()
                1 -> {
                    notesApp.choiceArchives(notesApp.archives)
                }

                2 -> break
            }
        } catch (e: InputMismatchException) {
            println("Ошибка ввода. Введите число")
        }
        scanner.nextLine()
    }
}
