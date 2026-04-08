<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500&display=swap" rel="stylesheet">

    <style>
        * {
          margin: 0;
          padding: 0;
          box-sizing: border-box;
          font-family: "Montserrat", sans-serif;
        }

        body {
          background-color: #f8f9fa;
          color: #333;
          padding: 40px 20px;
          min-height: 100vh;
          display: flex;
          flex-direction: column;
          align-items: center;
        }

        h1 {
          color: #5D3559;
          text-align: center;
          font-size: 2.8rem;
          font-weight: 400;
          margin-bottom: 10px;
        }

        h2 {
          color: #5D3559;
          font-weight: 500;
          margin: 40px 0 20px;
        }

        .container {
          width: 100%;
          max-width: 900px;
        }

        /* Карточка рецензии */
        .review-card {
          background-color: #fff;
          border-left: 5px solid #E6E6FA;
          border-radius: 12px;
          box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
          margin-bottom: 25px;
          padding: 25px 30px;
          transition: all 0.3s ease;
        }

        .review-card:hover {
          transform: translateY(-3px);
          box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }

        .review-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          border-bottom: 1px solid #eee;
          padding-bottom: 10px;
          margin-bottom: 15px;
        }

        .review-title {
          color: #5D3559;
          font-size: 1.4rem;
          font-weight: 500;
        }

        .review-rating {
          font-size: 1.4rem;
          font-weight: 600;
          color: #5D3559;
        }

        .review-brand {
          font-size: 0.95rem;
          color: #777;
          font-weight: 500;
          margin-top: -5px;
        }

        .review-description {
          color: #555;
          line-height: 1.6;
          font-size: 1rem;
          margin-top: 10px;
          white-space: pre-line;
        }

        /* Кнопка возврата */
        .custom-btn {
          background-color: #5D3559;
          color: white;
          border: none;
          border-radius: 22px;
          width: 260px;
          height: 60px;
          font-size: 1.2rem;
          font-weight: 500;
          cursor: pointer;
          text-decoration: none;
          line-height: 60px;
          text-align: center;
          margin: 40px auto 20px;
          transition: all 0.3s ease;
          box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
          display: block;
        }

        .custom-btn:hover {
          background-color: #6e3f6b;
          transform: translateY(-3px);
        }

        footer {
          margin-top: 40px;
          font-size: 0.9rem;
          color: #777;
          text-align: center;
        }

        @media (max-width: 768px) {
          .review-card {
            padding: 20px;
          }

          .review-title {
            font-size: 1.2rem;
          }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>My Profile: <span th:text="${username}"></span></h1>
    <h2>Your Reviews</h2>

    <div th:if="${#lists.isEmpty(reviews)}" style="text-align: center; color: #666; margin-top: 30px;">
        <p>You haven't written any reviews yet.</p>
    </div>

    <div th:each="review : ${reviews}" class="review-card">
        <div class="review-header">
            <div>
                <h3 class="review-title" th:text="${review.title}">Amazing product!</h3>
                <p class="review-brand" th:text="${review.brand.name}">Brand Name</p>
            </div>
            <span class="review-rating" th:text="${review.rating} + '/10'">9/10</span>
        </div>

        <div class="review-description" th:text="${review.description}">
            This is the full text of the user's review describing the product, impressions, and experience.
        </div>
    </div>

    <a th:href="@{/home}" class="custom-btn">← Back to Main Menu</a>
</div>

<footer>
    <p>&copy; 2025 SmokedByModel. All rights reserved.</p>
</footer>
</body>
</html>
