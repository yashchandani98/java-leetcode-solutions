 import java.util.*;

public class TextEditor {
        private StringBuilder text;
        private int cursorPosition;
        private Stack<EditAction> undoStack;
        private Stack<EditAction> redoStack;

        public TextEditor() {
            text = new StringBuilder();
            cursorPosition = 0;
            undoStack = new Stack<>();
            redoStack = new Stack<>();
        }

        // Append text to the document at the cursor position
        public void appendText(String newText) {
            text.insert(cursorPosition, newText);
            cursorPosition += newText.length();
            saveAction(new AppendAction(newText));
        }

        // Replace text at the cursor position
        public void replaceText(String replacement) {
            String oldText = text.substring(cursorPosition, cursorPosition + replacement.length());
            text.replace(cursorPosition, cursorPosition + oldText.length(), replacement);
            saveAction(new ReplaceAction(oldText, replacement));
            cursorPosition += replacement.length();
        }

        // Move cursor left
        public void moveLeft() {
            if (cursorPosition > 0) {
                cursorPosition--;
            }
        }

        // Move cursor right
        public void moveRight() {
            if (cursorPosition < text.length()) {
                cursorPosition++;
            }
        }

        // Move cursor up (simulate by moving cursor back one line; adjust as needed)
        public void moveUp(int linesPerPage) {
            cursorPosition = Math.max(0, cursorPosition - linesPerPage);
        }

        // Move cursor down (simulate by moving cursor forward one line; adjust as needed)
        public void moveDown(int linesPerPage) {
            cursorPosition = Math.min(text.length(), cursorPosition + linesPerPage);
        }

        // Page up, move cursor up by a page
        public void pageUp(int linesPerPage) {
            moveUp(linesPerPage);
        }

        // Page down, move cursor down by a page
        public void pageDown(int linesPerPage) {
            moveDown(linesPerPage);
        }

        // Undo the last action
        public void undo() {
            if (!undoStack.isEmpty()) {
                EditAction lastAction = undoStack.pop();
                lastAction.undo();
                redoStack.push(lastAction);
            }
        }

        // Redo the last undone action
        public void redo() {
            if (!redoStack.isEmpty()) {
                EditAction lastUndoneAction = redoStack.pop();
                lastUndoneAction.redo();
                undoStack.push(lastUndoneAction);
            }
        }

        // Print the current state of the document
        public void printDocument() {
            System.out.println("Document:");
            System.out.println(text);
            System.out.println("Cursor at position: " + cursorPosition);
        }

        // Save actions to undo/redo stack
        private void saveAction(EditAction action) {
            undoStack.push(action);
            redoStack.clear();  // Clear redo stack after a new action
        }

        // Action interface for Undo/Redo functionality
        interface EditAction {
            void undo();
            void redo();
        }

        // AppendAction class
        class AppendAction implements EditAction {
            private final String textToAppend;

            AppendAction(String textToAppend) {
                this.textToAppend = textToAppend;
            }

            @Override
            public void undo() {
                text.delete(cursorPosition - textToAppend.length(), cursorPosition);
                cursorPosition -= textToAppend.length();
            }

            @Override
            public void redo() {
                text.insert(cursorPosition, textToAppend);
                cursorPosition += textToAppend.length();
            }
        }

        // ReplaceAction class
        class ReplaceAction implements EditAction {
            private final String oldText;
            private final String newText;

            ReplaceAction(String oldText, String newText) {
                this.oldText = oldText;
                this.newText = newText;
            }

            @Override
            public void undo() {
                text.replace(cursorPosition - newText.length(), cursorPosition, oldText);
                cursorPosition -= newText.length();
            }

            @Override
            public void redo() {
                text.replace(cursorPosition - oldText.length(), cursorPosition, newText);
                cursorPosition += newText.length();
            }
        }

        public static void main(String[] args) {
            TextEditor editor = new TextEditor();

            // Sample usage
            editor.appendText("Hello, world! ");
            editor.printDocument();
            editor.appendText("This is a text editor.");
            editor.printDocument();
            editor.replaceText("simple text editor");
            editor.printDocument();

            // Navigation (Arrow keys and Page Up/Down)
            editor.moveLeft();
            editor.printDocument();
            editor.moveRight();
            editor.printDocument();
            editor.pageUp(5);
            editor.printDocument();
            editor.pageDown(5);
            editor.printDocument();

            // Undo and Redo
            editor.undo();
            editor.printDocument();
            editor.redo();
            editor.printDocument();
        }
}
