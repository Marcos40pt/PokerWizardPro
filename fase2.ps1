$ErrorActionPreference = 'Stop'

function Ensure-Dir([string]$p){ if(-not (Test-Path $p)){ New-Item -ItemType Directory -Force -Path $p | Out-Null } }
function Write-File([string]$p,[string]$c){ Ensure-Dir (Split-Path $p -Parent); Set-Content -Encoding UTF8 -Path $p -Value $c }

if(-not (Test-Path 'settings.gradle.kts')){ throw 'Corre na raiz do projeto (onde está settings.gradle.kts)' }

# (1) Garantir pastas
Ensure-Dir 'core/domain/src/main/java/pt/marcos/pokerwizardpro/core/domain/training/usecases'
Ensure-Dir 'core/data/src/main/java/pt/marcos/pokerwizardpro/core/data/db'
Ensure-Dir 'core/data/src/main/java/pt/marcos/pokerwizardpro/core/data/training'
Ensure-Dir 'app/src/main/java/pt/marcos/pokerwizardpro/di'
Ensure-Dir 'app/src/main/java/pt/marcos/pokerwizardpro/training'

# (2) Escrever ficheiros (conteúdo vem do repo via copy/paste manual para evitar erros)
Write-Host 'OK: estrutura criada. Agora vou pedir 2 ações no Android Studio.' -ForegroundColor Green
