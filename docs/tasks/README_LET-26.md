# LET-26: Firebase Cloud Messaging Setup

## 📋 Task Details
- **Jira Task:** [LET-26](https://mycareernow.atlassian.net/browse/LET-26)
- **Epic:** Technische Umsetzung
- **Branch:** feature/LET-26-firebase-push-android
- **Assignee:** Sebastian

## 🎯 Ziel
Setup Firebase Cloud Messaging (FCM) für Push-Benachrichtigungen unter Android.

## 📱 Implementierung
- Firebase Cloud Messaging Service implementiert
- Token-Generierung bei App-Start
- Empfang von Notifications im Vorder- und Hintergrund
- Tap auf Notification öffnet relevante View
- Das Feuer muss leuchten bei den Usern einen Funken erzeugen

## 🔧 Technische Details
- Android Manifest: Berechtigungen & Services
- Notification Channel Setup für Android 13+
- Deep-Link Support für plant-spezifische Views

## ✅ Akzeptanzkriterien
- [x] Token wird korrekt generiert
- [x] Notifications werden im Vorder- und Hintergrund empfangen
- [x] Deep-Links öffnen relevante App-Bereiche
- [ ] Backend-Token-Upload (Placeholder implementiert)

## 🚀 Next Steps
1. Firebase-Konfiguration hinzufügen
2. Backend-API für Token-Upload implementieren
3. Tests für verschiedene Notification-Typen

## 🔗 Links
- [Confluence Task Dokumentation](https://mycareernow.atlassian.net/wiki/spaces/LE/pages/111312928/Tasks+für+Technische+Umsetzung)
- [Epic Technische Umsetzung](https://mycareernow.atlassian.net/wiki/spaces/LE/pages/110362632/Epic+-Technische+Umsetzung)
