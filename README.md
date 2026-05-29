<div align="center">

# 🌐 Connect-Hub

### A Feature-Rich Social Networking Platform Built with Java Swing

[![Java](https://img.shields.io/badge/Java-11+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![JSON](https://img.shields.io/badge/Storage-JSON-292929?style=for-the-badge&logo=json&logoColor=white)]()
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-007396?style=for-the-badge)]()

---

**Connect-Hub** is a fully functional desktop social networking platform that mirrors the core features of modern social media — user accounts, friend management, group communities, a real-time news feed, chat messaging, content creation, and a smart notification system — all built from scratch in Java with a Swing GUI and JSON file-based persistence.

[Features](#-features) · [Architecture](#-architecture--design-patterns) · [Installation](#-installation) · [Quick Start](#-quick-start) · [Project Structure](#-project-structure)

</div>

---

## ✨ Features

| Module | Capabilities |
|--------|-------------|
| 🔐 **User Management** | Sign up with email validation, SHA-256 password hashing, login/logout with status tracking |
| 👥 **Friend System** | Send/accept/decline friend requests, block & remove friends, mutual friend tracking |
| 🏘️ **Group Management** | Create groups with photo/description, join requests & approvals, role hierarchy (Creator → Admin → User), promote/demote members, remove users |
| 📝 **Content Creation** | Create **Posts** (persistent) and **Stories** (auto-expire after 24h), attach images, publish to personal feed or groups |
| 💬 **Real-Time Chat** | Direct messaging between friends, full conversation history with timestamps |
| 📰 **News Feed** | Aggregated feed from friends' posts + group content, friend & group suggestions engine |
| 🔔 **Notifications** | Friend requests, group join activity, new comments/likes, chat messages, admin promotions/demotions |
| 🔍 **Search** | Search for users and groups by name, view profiles, send friend requests directly from results |
| 👤 **Profile Management** | Edit bio, set profile picture & cover photo, view activity history |
| ❤️ **Interactions** | Like/unlike posts (toggle), comment on content, comment iteration |

---

## 🏗️ Architecture & Design Patterns

Connect-Hub is engineered with clean object-oriented principles and leverages several **Gang of Four design patterns**:

```
┌─────────────────────────────────────────────────────────────┐
│                        PRESENTATION                         │
│  Firstpage ─→ LoginForm / SignUpForm ─→ NewsFeedPage        │
│  ProfileGUI · MessageManagerGUI · NotificationGUI           │
│  SearchBar · GroupDialogues · FriendManagementPage           │
├─────────────────────────────────────────────────────────────┤
│                       BUSINESS LOGIC                        │
│  NewsFeed Engine · FriendRequests · Group Builder            │
│  CommentBuilder · LikeFactory · Notification System         │
├─────────────────────────────────────────────────────────────┤
│                       DATA / PERSISTENCE                    │
│  json.java (CRUD Engine) ─→ users.json / chats.json         │
└─────────────────────────────────────────────────────────────┘
```

| Pattern | Where It's Used | Purpose |
|---------|----------------|---------|
| **Observer** | `Observer.java` → `MessageSender` | Chat messages trigger persistence through observer notification |
| **Decorator** | `NotificationInterface` → `NotificationDecorator` → Friend/Group/Interaction decorators | Dynamically compose notification types without subclass explosion |
| **Builder** | `GroupBuilder`, `CommentBuilder` | Step-by-step construction of complex Group and Comment objects |
| **Factory** | `LikeFactory` | Centralized like creation with duplicate detection and toggle logic |
| **Iterator** | `CommentIterator` | Bidirectional traversal of comment collections |
| **Strategy** | `Contentcreation` interface → `Post`, `Story` | Interchangeable content creation algorithms |
| **Template Method** | `Profile` → `User` | Base profile provides structure; User specializes authentication |

---

## 📁 Project Structure

```
Connect-Hub/
├── 📄 README.md                          # You are here
├── 📄 LICENSE                            # MIT License
├── 📄 .gitignore                         # Java/IDE exclusions
├── 📄 pom.xml                            # Maven dependency manifest
│
├── 📂 src/                               # Java Source Code
│   ├── Main.java                         # 🚀 Application entry point
│   ├── 📂 UserManagementSystem/          # Authentication, validation, password hashing
│   ├── 📂 ContentCreation/               # Posts, stories, profiles, JSON persistence engine
│   ├── 📂 FriendManagement/              # Friend requests, blocking, friend list management
│   ├── 📂 GroupManagement/               # Groups, roles, join requests, group content
│   ├── 📂 Chatting/                      # Direct messaging between users
│   ├── 📂 NewsFeed/                      # Feed aggregation, friend/group suggestions
│   ├── 📂 NotificationSystem/            # Observer + Decorator notification pipeline
│   ├── 📂 Commenting/                    # Comments, likes, iteration
│   ├── 📂 SearchBar/                     # User and group search functionality
│   └── 📂 ProfileManagement/            # Profile editing GUI
│
├── 📂 data/                              # JSON database files
│   ├── users.json                        # User profiles, content, friends, groups
│   └── chats.json                        # Chat message history
│
└── 📂 lib/                               # External JAR dependencies
    ├── json-20210307.jar                 # org.json library
    └── json-simple-1.1.jar              # JSON.simple library
```

---

## 🚀 Installation

### Prerequisites

- **Java JDK 11** or higher — [Download here](https://adoptium.net/)
- **Git** — [Download here](https://git-scm.com/downloads)

### Clone the Repository

```bash
git clone https://github.com/dandyidk/Connect-Hub.git
cd Connect-Hub
```

### Option A: Compile with Maven (Recommended)

```bash
# Build the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="Main"
```

### Option B: Compile Manually with javac

```bash
# Compile all source files with the required libraries
javac -cp "lib/json-20210307.jar;lib/json-simple-1.1.jar" -d build src/*.java src/**/*.java

# Run the application
java -cp "build;lib/json-20210307.jar;lib/json-simple-1.1.jar" Main
```

> **Note:** On macOS/Linux, replace `;` with `:` in the classpath separator.

### Option C: Open in NetBeans

1. Open NetBeans IDE
2. `File` → `Open Project` → Select the `Connect-Hub` folder
3. Right-click the project → `Properties` → `Libraries` → Add the JARs from `lib/`
4. Click **Run** ▶️

---

## ⚡ Quick Start

1. **Launch** the application — you'll see the landing page with **Sign Up** and **Login** buttons.

2. **Create an account** — Click Sign Up, enter your email, username, password, and date of birth. All inputs are validated in real-time.

3. **Log in** — Use your email and password. Your status changes to "Online".

4. **Explore the News Feed** — See posts from friends and groups, discover friend & group suggestions.

5. **Connect with others** — Search for users, send friend requests, join groups.

6. **Create content** — Publish posts (permanent) or stories (expire in 24 hours) with optional images.

7. **Chat** — Open direct messages with friends for real-time conversation.

8. **Stay notified** — The notification panel shows friend requests, group activity, new comments/likes, and chat messages.

---

## 🛡️ Security

- Passwords are **never stored in plaintext** — all passwords are hashed using **SHA-256** before persistence.
- Input validation is enforced on email format, date of birth, and required fields.
- Friend request status tracking prevents duplicate or invalid requests.

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📜 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

**Built with ❤️ in Java**

*Connect-Hub — Where connections happen.*

</div>