@LoginProfile
Feature: Тестируем страницу авторизации
  сайта automationpractice.com с водом
  заведомо неверных данных и проверяем
  отображаемые сообщения об ошибках

  Scenario: Неудачная авторизация
    Given Я нахожусь на домашней странице
    Then я выбираю 'Ноутбуки и компьютеры'
    And  нажимаю пункт 'Мониторы'
    Then  подождем '5' сек