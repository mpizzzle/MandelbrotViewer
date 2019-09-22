#include <SFML/Graphics.hpp>
#include <iostream>

static const char frag_shader[] = "src/frag.glsl";
static const int window_w = 1920;
static const int window_h = 1080;
static const int steps_per_frame_normal = 3;

static int iterations = 200;
static float cam_x = 0;
static float cam_y = 0;
static float zoom = 0.2f;
static float delta = 0.01f;

int main(int argc, char *argv[]) {
  if (!sf::Shader::isAvailable()) {
      std::cout << "Your graphics card is bad";
    return 1;
  }

  sf::Shader shader;
  if (!shader.loadFromFile(frag_shader, sf::Shader::Fragment)) {
      std::cout << "Failed to compile fragment shader!";
    return 1;
  }

  sf::ContextSettings settings;
  settings.depthBits = 24;
  settings.stencilBits = 8;
  settings.antialiasingLevel = 4;
  settings.majorVersion = 3;
  settings.minorVersion = 0;

  const sf::VideoMode screenSize = sf::VideoMode::getDesktopMode();
  sf::RenderWindow window(screenSize, "Mandelbrot", sf::Style::Resize | sf::Style::Fullscreen, settings);

  window.setMouseCursorVisible(false);
  window.setFramerateLimit(60);
  window.setVerticalSyncEnabled(true);
  window.setActive(false);
  window.requestFocus();
  sf::Clock clock;

  sf::Texture texture; texture.create(window_w, window_h);
  sf::Sprite sprite; sprite.setTexture(texture);

  while (window.isOpen()) {
    sf::Event event;
    while (window.pollEvent(event)) {
      if (event.type == sf::Event::Closed) {
        window.close();
        break;
      } else if (event.type == sf::Event::KeyPressed) {
        const sf::Keyboard::Key keycode = event.key.code;
        if (keycode == sf::Keyboard::Escape) {
          window.close();
          break;
        } else if (keycode == sf::Keyboard::Z) {
        zoom += zoom*delta;
        } else if (keycode == sf::Keyboard::X) {
        zoom -= zoom*delta;
        } else if (keycode == sf::Keyboard::H) {
        cam_x -= (1 / zoom) * delta;
        } else if (keycode == sf::Keyboard::J) {
        cam_y += (1 / zoom) * delta;
        } else if (keycode == sf::Keyboard::K) {
        cam_y -= (1 / zoom) * delta;
        } else if (keycode == sf::Keyboard::L) {
        cam_x += (1 / zoom) * delta;
        } else if (keycode == sf::Keyboard::I) {
        iterations += 1;
        } else if (keycode == sf::Keyboard::O) {
        iterations -= 1;
        }
      }
    }

    shader.setUniform("zoom", zoom);
    shader.setUniform("iterations", iterations);
    shader.setUniform("cam_x", cam_x);
    shader.setUniform("cam_y", cam_y);

    window.clear();
    window.draw(sprite, &shader);
    window.display();
  }

  return 0;
}
