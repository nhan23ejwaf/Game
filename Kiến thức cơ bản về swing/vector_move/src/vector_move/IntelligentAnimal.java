package vector_move;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntelligentAnimal extends JPanel {
    private final List<Animal> animals; // Danh sách các con vật
    private final int panelWidth = 1500;
    private final int panelHeight = 850;
    private final int numAnimals = 5; // Số lượng con vật
    private double moveSpeed = 5; // Tốc độ di chuyển mặc định

    public IntelligentAnimal() {
        animals = new ArrayList<>();

        // Tạo số lượng đối tượng Animal ngẫu nhiên và đảm bảo không chồng lên nhau
        for (int i = 0; i < numAnimals; i++) {
            animals.add(createRandomAnimal()); // Tạo con vật với vị trí ngẫu nhiên
        }

        // Tạo Timer để cập nhật chuyển động
        Timer timer = new Timer(30, e -> {
            for (Animal animal : animals) {
                animal.senseAndMove(panelWidth, panelHeight, animals, moveSpeed); // Di chuyển và cảm nhận
            }
            repaint(); // Vẽ lại
        });
        timer.start();

        // Tạo slider để điều chỉnh tốc độ di chuyển
        JSlider speedSlider = new JSlider(1, 20, (int) moveSpeed);
        speedSlider.setBounds(10, panelHeight - 50, 200, 30);
        speedSlider.addChangeListener(e -> moveSpeed = speedSlider.getValue());
        this.add(speedSlider);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Vẽ nền
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, panelWidth, panelHeight);

        // Vẽ các con vật
        for (Animal animal : animals) {
            animal.draw(g2d);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Intelligent Animal Movement");
        IntelligentAnimal panel = new IntelligentAnimal();
        frame.add(panel);
        frame.setSize(1500, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Hàm tạo Animal với vị trí ngẫu nhiên và không chồng lên nhau
    private Animal createRandomAnimal() {
        Random rand = new Random();
        int x, y;

        // Lặp cho đến khi tìm được vị trí không chồng lên con vật khác
        do {
            x = rand.nextInt(panelWidth);
            y = rand.nextInt(panelHeight);
        } while (isPositionOccupied(x, y));

        return new Animal(x, y);
    }

    // Kiểm tra nếu vị trí (x, y) đã bị chiếm
    private boolean isPositionOccupied(int x, int y) {
        for (Animal animal : animals) {
            int dx = x - animal.x;
            int dy = y - animal.y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < animal.size * 2) { // Nếu con vật khác quá gần
                return true;
            }
        }
        return false;
    }

    // Class đại diện cho con vật
    static class Animal {
        private int x, y; // Vị trí tâm của tam giác
        private final int size = 10; // Kích thước tam giác
        private double direction; // Hướng di chuyển (góc tính bằng radian)
        private final Random random = new Random();

        public Animal(int x, int y) {
            this.x = x;
            this.y = y;
            direction = random.nextDouble() * 2 * Math.PI; // Hướng ngẫu nhiên ban đầu
        }

        // Vẽ con vật (tam giác)
        public void draw(Graphics2D g2d) {
            // Tọa độ các đỉnh tam giác dựa vào vị trí và hướng
            int[] xPoints = {
                x + (int) (size * Math.cos(direction)), // Đỉnh nhọn
                x + (int) (size * Math.cos(direction + Math.PI * 3 / 4)), // Góc dưới trái
                x + (int) (size * Math.cos(direction - Math.PI * 3 / 4))  // Góc dưới phải
            };
            int[] yPoints = {
                y + (int) (size * Math.sin(direction)), // Đỉnh nhọn
                y + (int) (size * Math.sin(direction + Math.PI * 3 / 4)), // Góc dưới trái
                y + (int) (size * Math.sin(direction - Math.PI * 3 / 4))  // Góc dưới phải
            };

            // Vẽ tam giác
            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(xPoints, yPoints, 3); // Tô màu
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(xPoints, yPoints, 3); // Vẽ đường viền
        }

        // Di chuyển với cảm nhận biên giới hạn và va chạm với các đối tượng khác
        public void senseAndMove(int width, int height, List<Animal> animals, double moveSpeed) {
            // Tính toán tọa độ của đỉnh nhọn
            int headX = x + (int) (size * Math.cos(direction));
            int headY = y + (int) (size * Math.sin(direction));

            // Nếu đỉnh nhọn sắp chạm biên, đổi hướng
            if (headX < 0 || headX > width || headY < 0 || headY > height) {
                direction += Math.PI; // Quay đầu lại
                direction += (random.nextDouble() - 0.5) * Math.PI / 4; // Thêm độ lệch nhỏ
            } else if (random.nextDouble() < 0.05) { // 5% cơ hội đổi hướng ngẫu nhiên
                direction += (random.nextDouble() - 0.5) * Math.PI / 2; // Đổi hướng nhẹ
            }

            // Kiểm tra va chạm với các đối tượng khác
            for (Animal other : animals) {
                if (other != this && isColliding(other)) {
                    direction += Math.PI; // Quay đầu lại khi va chạm
                    direction += (random.nextDouble() - 0.5) * Math.PI / 4; // Thêm độ lệch nhỏ
                }
            }

            // Tính toán bước di chuyển
            int step = (int) moveSpeed; // Tốc độ từ thuộc tính moveSpeed
            x += (int) (step * Math.cos(direction));
            y += (int) (step * Math.sin(direction));

            // Giữ vị trí trong biên
            x = Math.max(0, Math.min(x, width));
            y = Math.max(0, Math.min(y, height));
        }

        // Kiểm tra va chạm với đối tượng khác
        private boolean isColliding(Animal other) {
            int dx = x - other.x;
            int dy = y - other.y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            return distance < size * 2; // Kiểm tra nếu khoảng cách giữa hai con vật nhỏ hơn kích thước của chúng
        }
    }
}
