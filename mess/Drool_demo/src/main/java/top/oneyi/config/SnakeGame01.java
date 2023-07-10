package top.oneyi.config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame01 extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int DOT_SIZE = 10;
    private static final int ALL_DOTS = (WIDTH * HEIGHT) / (DOT_SIZE * DOT_SIZE);
    private static final int RAND_POS = (WIDTH / DOT_SIZE) * (HEIGHT / DOT_SIZE);
    private static final int DELAY = 140;

    private ArrayList<Point> snake;
    private Point food;
    private Timer timer;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private Random random;
    private boolean isReward = false; // 是否为奖励果实

    public SnakeGame01() {
        initGame();
    }

    private void initGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        snake = new ArrayList<Point>();
        random = new Random();
        initSnake();
        initFood();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initSnake() {
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));
        snake.add(new Point(DOT_SIZE, 0));
        snake.add(new Point(DOT_SIZE * 2, 0));
    }

    private void initFood() {
        int x = random.nextInt((WIDTH / DOT_SIZE - 1)) * DOT_SIZE;
        int y = random.nextInt((HEIGHT / DOT_SIZE - 1)) * DOT_SIZE;
        isReward = random.nextInt(10) == 0; // 1/10 的概率生成奖励果实
        food = new Point(x, y);
    }


    private void move() {
        Point head = snake.get(0);
        int x = head.x;
        int y = head.y;
        if (leftDirection) {
            x -= DOT_SIZE;
        } else if (rightDirection) {
            x += DOT_SIZE;
        } else if (upDirection) {
            y -= DOT_SIZE;
        } else if (downDirection) {
            y += DOT_SIZE;
        }
        Point newHead = new Point(x, y);
        if (newHead.equals(food)) {
            if (isReward) { // 如果是奖励果实，则增加蛇的长度和分数
                snake.add(0, newHead);
                snake.add(0, newHead);
                snake.add(0, newHead);
            } else {
                snake.add(0, newHead);
            }
            initFood();
        } else {
            snake.remove(snake.size() - 1);
            snake.add(0, newHead);
        }
        checkCollision();
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            inGame = false;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                inGame = false;
            }
        }
    }

    private void drawSnake(Graphics g) {
        for (Point p : snake) {
            g.setColor(Color.GREEN);
            g.fillRect(p.x, p.y, DOT_SIZE, DOT_SIZE);
        }
    }

    private void drawFood(Graphics g) {
        if (isReward) { // 如果是奖励果实，则绘制黄色
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.RED);
        }
        g.fillRect(food.x, food.y, DOT_SIZE, DOT_SIZE);
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Game Over", WIDTH / 2 - 30, HEIGHT / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            drawSnake(g);
            drawFood(g);
        } else {
            gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && !rightDirection) {
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        } else if (key == KeyEvent.VK_RIGHT && !leftDirection) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        } else if (key == KeyEvent.VK_UP && !downDirection) {
            upDirection = true;
            leftDirection = false;
            rightDirection = false;
        } else if (key == KeyEvent.VK_DOWN && !upDirection) {
            downDirection = true;
            leftDirection = false;
            rightDirection = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SnakeGame01());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
