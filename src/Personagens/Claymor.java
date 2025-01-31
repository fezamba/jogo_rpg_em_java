package Personagens;
public class Claymor extends PersonagemBase implements Personagem {
    private double reduzirDano;

    public Claymor() {
        super("Claymor", 600, 100, 300, 600, 300);
    }

    @Override
    public void contarHistoria() {
        System.out.println("Um cavaleiro veterano, mestre em defesa. Ele sobreviveu a batalhas impossíveis graças à sua resiliência inigualável. Sua missão é proteger os outros heróis a qualquer custo.\n");
    }

    @Override
    public void utilizarPoder() {
        System.out.println(nome + " lança um ataque mágico básico!");
    }

    @Override
    public void utilizarPoderEspecial() {
        int custoMana = 300;
        if (mp >= custoMana){
            mp -= custoMana;
            reduzirDano = 0.5;
            System.out.println(nome + " ativa um escudo mágico que reduz o do dano recebido!");
            System.out.println("Mana restante: " + mp);
        } else {
            System.out.println(nome + " não tem mana suficiente para ativar o escudo!");
        }
    }

    @Override
    public void adicionarItem(Item item) {
        inventario.add(item);
        System.out.println(item.getNome() + " foi adicionado ao inventário!");
    }

    @Override
    public void usarItem(int indice) {
        if (indice >= 0 && indice < inventario.size()) {
            Item item = inventario.remove(indice);

            if (item instanceof PocaoVida) {
                setHp(hp + item.getPoder());
                System.out.println(nome + " usou " + item.getNome() + " e recuperou " + item.getPoder() + " HP!");
            } else if (item instanceof PocaoMana) {
                setMp(mp + item.getPoder());
                System.out.println(nome + " usou " + item.getNome() + " e recuperou " + item.getPoder() + " MP!");
            } else {
                System.out.println(nome + " usou " + item.getNome() + ", mas não teve efeito.");
            }
        } else {
            System.out.println("Índice inválido. Não foi possível usar o item.");
        }
    }

    @Override
    public void exibirInventario() {
        if (inventario.isEmpty()) {
            System.out.println("Seu inventário está vazio!");
            return;
        }

        System.out.println("Inventário:");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println((i + 1) + " - " + inventario.get(i).toString());
        }
    }


    @Override
    public void receberDano(int dano){
        int danoFinal = (int)(dano * (1 - reduzirDano));
        setHp(hp - danoFinal);
        System.out.println(nome + " recebeu " + danoFinal + " de dano (redução de " + (reduzirDano * 100) + "%). Vida atual: " + hp + "/" + hpMax);
    }
}
