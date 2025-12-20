export function increment(current: number, max = 10) {
  if (current < max) {
    return current++
  }
  return current
}
