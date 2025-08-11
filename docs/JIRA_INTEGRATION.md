# GitHub-Jira Integration Setup

## 🔗 Automatische Verknüpfung

Dieses Repository ist mit dem LetItGrow Jira-Projekt verknüpft:
- **Jira Projekt:** LET - LetItGrow  
- **Confluence:** [LetItGrow Documentation](https://mycareernow.atlassian.net/wiki/spaces/LE/overview)

## 📋 Branch & Commit Conventions

### Branch Naming:
```
feature/LET-XX-short-description
bugfix/LET-XX-short-description  
hotfix/LET-XX-short-description
docs/LET-XX-short-description
```

### Commit Messages:
```
LET-XX: Short description

- Detailed change 1
- Detailed change 2
- Implementation notes

Resolves: LET-XX
Epic: [Epic Name]
Confluence: [Link zu relevanter Doku]
```

## 🚀 Aktueller Branch
- **Branch:** feature/LET-26-firebase-push-android
- **Task:** [LET-26 - Firebase Cloud Messaging](https://mycareernow.atlassian.net/browse/LET-26)
- **Status:** Implementation Complete, Ready for Review

## 📁 Projektstruktur
```
atlassian_letitgrow/
├── android/                    # Android App
│   └── app/
│       ├── build.gradle       # LET-26: Firebase Dependencies
│       └── src/main/java/com/letitgrow/
│           └── notifications/
│               └── FirebasePushService.kt  # LET-26: Push Service
├── ios/                       # iOS App (geplant)
├── backend/                   # Backend API (geplant)
├── docs/                      # Dokumentation
│   └── tasks/                 # Task-spezifische Docs
│       └── README_LET-26.md   # LET-26: Implementation Guide
└── README.md                  # Projekt Overview
```

## 🏷️ Labels & Tags
- `Technik` - Technische Implementation
- `Firebase` - Firebase-bezogene Changes  
- `Push-Notifications` - Notification System
- `Android` - Android-spezifische Features
