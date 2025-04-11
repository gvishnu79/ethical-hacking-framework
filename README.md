# Ethical Hacking AI CLI Toolkit

A powerful, modular Command Line Interface (CLI) toolkit built in Java to assist ethical hackers and cybersecurity enthusiasts. It features AI-assisted security tools such as FGSM adversarial attacks, port scanning with banner grabbing, OS fingerprinting, subdomain enumeration, and SYN scanning.

---

## 🚀 Features

- 🔐 **FGSM Attack** – Apply Fast Gradient Sign Method to simulate adversarial examples using image input or raw data.
- 🌐 **Port Scanning** – Scan open/closed ports and retrieve banners for services.
- 📡 **SYN Scan** – Perform TCP SYN scans (half-open scan) on target hosts.
- 🕵️ **Subdomain Scanning** – Brute-force subdomain discovery with wordlist support.
- 🧠 **OS Fingerprinting** – Heuristic OS detection using TTL analysis.
- 🧩 Modular Design – Each module is plug-and-play using [Picocli](https://picocli.info).

---

## 📂 Project Structure
ethical-hacking-ai/ │ ├── src/main/java/com/ethicalhackingai/ │ ├── cli/ # Command implementations │ ├── recon/ # Reconnaissance tools (banner grabber, scanners) │ ├── service/ # Service logic │ ├── model/ # Data models (e.g., ScanResult) │ └── utils/ # Utilities (e.g., image loader) │ ├── src/main/resources/ # Resource files like input images or wordlists ├── pom.xml # Maven build configuration └── README.md


---

## 🛠️ Setup Instructions

1. **Clone the Repo**
   ```bash
   git clone https://github.com/yourusername/ethical-hacking-ai.git
   cd ethical-hacking-ai
**2.**Build with Maven**
mvn clean package

**3.Run from IntelliJ**

Open the project in IntelliJ IDEA.

Go to Run → Edit Configurations.

Add ethicalhackingai as the main class.

Pass arguments like fgsm, synscan, etc., in the Program arguments field.

**4. Run via Terminal**

java -jar target/EthicalHackingAI.jar --help

🧪 Usage Examples

✅ List All Commands
java -jar target/EthicalHackingAI.jar --help

🔐 FGSM Attack (Raw Input)
java -jar target/EthicalHackingAI.jar fgsm -i=0.1,0.2,0.3 -g=0.01,0.02,0.03 -e=0.5

📷 FGSM Attack (Image Input)
java -jar target/EthicalHackingAI.jar fgsm -f=src/main/resources/input.png -e=0.5 -o=output.png

🌐 Port Scanner
java -jar target/EthicalHackingAI.jar subdomain -d example.com -w src/main/resources/subdomains.txt

🕵️ Subdomain Scan
java -jar target/EthicalHackingAI.jar subdomain -d example.com -w src/main/resources/subdomains.txt

📡 SYN Scanner
java -jar target/EthicalHackingAI.jar synscan -h 192.168.1.1 -p 22,80,443

🧠 OS Fingerprinting
java -jar target/EthicalHackingAI.jar osfingerprint -h 192.168.1.1

⚠️ Disclaimer
This tool is intended for educational and ethical hacking purposes only. Do not use it on networks or systems without proper authorization. Always follow local laws and regulations.

👨‍💻 Author
Built with ❤️ by G VISHNU

📜 License
This project is licensed under the MIT License.

---

Let me know if you'd like this customized with a project logo, more usage examples, badges (build status, license), or GitHub Actions integration!

