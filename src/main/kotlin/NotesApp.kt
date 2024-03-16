import java.util.InputMismatchException
import java.util.Scanner


class NotesApp {
    val archives = mutableListOf<Archive>()
    val scanner = Scanner(System.`in`)

    fun createArchive() {
        while (true) {
            println("Введите название архива")
            try {
                val archiveTitle = scanner.nextLine()
                if (archiveTitle.isNotEmpty()) {
                    archives.add(Archive(archiveTitle!!))
                    println("Архив создан")
                    break
                } else {
                    println("Ошибка ввода: пустая строка")
                }
            } catch (e: InputMismatchException) {
                println("Ошибка ввода. Введите название архива")
                scanner.next()
            }
        }
    }

    fun choiceArchives(archives: MutableList<Archive>) {
        while (true) {
            if (archives.isEmpty()) {
                println("Архив пуст")
                break
            }
            println("Выберите архив:")
            archives.forEachIndexed { index, archive -> println("${index + 1} - ${archive.title}") }
            println("${archives.size + 1} - Назад")
            val input = readLine()
            try {
                val selectedArchiveIndex = input?.toInt()?.minus(1)
                if ((selectedArchiveIndex != null) && (selectedArchiveIndex in archives.indices)) {
                    choiceNotes(archives[selectedArchiveIndex])
                } else if (input == (archives.size + 1).toString()) {
                    return
                } else {
                    println("Неправильный ввод")
                }
            } catch (e: NumberFormatException) {
                println("Ошибка ввода. Введите число")
            }
        }
    }

    fun createNote(archive: Archive) {
        while (true) {
            try {
                println("Введите название заметки")
                val noteTitle = scanner.nextLine()
                if (noteTitle.isEmpty()) {
                    println("Название заметки не может быть пустым")
                    continue
                }
                while (true) {
                    println("Введите текст заметки")
                    val noteText = scanner.nextLine()
                    if (noteText.isEmpty()) {
                        println("Текст заметки не может быть пустым")
                    } else {
                        archive.notes.add(Note(noteTitle!!, noteText))
                        break
                    }
                }
                println("Заметка создана")
                break
            } catch (e: InputMismatchException) {
                println("Ошибка ввода. Введите название заметки")
                scanner.next()
            }
        }
    }


    fun choiceNotes(archive: Archive) {
        while (true) {
            if (archive.notes.isEmpty()) {
                println("Заметка пустая")
            }
            println("Выберите пункт:")
            archive.notes.forEachIndexed { index, note ->
                println("${index + 1} - ${note.title}")
            }
            println("${archive.notes.size + 1} - Создать заметку")
            println("${archive.notes.size + 2} - Назад")
            val input = readLine()

            when (input) {
                (archive.notes.size + 1).toString() -> {
                    createNote(archive)
                }

                (archive.notes.size + 2).toString() -> return
                else -> {
                    val selectedNoteIndex = input?.toInt()?.minus(1)
                    if (selectedNoteIndex != null && selectedNoteIndex in archive.notes.indices) {
                        showNoteContent(archive.notes[selectedNoteIndex])
                    } else {
                        println("Неправильный ввод")
                    }
                }
            }
        }
    }

    fun showNoteContent(note: Note) {
        println("Заголовок: ${note.title}")
        println("Содержимое: ${note.text}")
        println("Нажмите кнопку ENTER для выхода в предыдущее меню")
        readLine()
    }
}
