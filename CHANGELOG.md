# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project follows [Semantic Versioning](https://semver.org/).

## [0.0.1] - 2026-02-11

### Added
- Account domain implementation with full lifecycle (create, update, activate, deactivate).
- Category domain with create and update operations.
- Centralized error handling via `GlobalExceptionMapper`.
- Unified business exception structure using `ApiException` and `ApiErrorCode`.
- Reusable OpenAPI error response annotations (`BadRequestResponse`, `NotFoundResponse`, `ConflictResponse`).
- Parameter validation utility (`Parameters`) for defensive checks.
- Defensive mapping for enum values stored in database.
- Package organization aligned with Clean Architecture:
  - `domain`
  - `application`
  - `infrastructure`
  - `interfaceadapter`
  - `shared`
